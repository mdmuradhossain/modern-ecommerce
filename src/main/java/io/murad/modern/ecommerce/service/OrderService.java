package io.murad.modern.ecommerce.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import io.murad.modern.ecommerce.database.model.Currency;
import io.murad.modern.ecommerce.database.model.Order;
import io.murad.modern.ecommerce.database.model.OrderItem;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.CartDto;
import io.murad.modern.ecommerce.dto.CartItemDto;
import io.murad.modern.ecommerce.dto.CheckoutItemDto;
import io.murad.modern.ecommerce.dto.PlaceOrderDto;
import io.murad.modern.ecommerce.exception.OrderNotFoundException;
import io.murad.modern.ecommerce.repository.CartRepository;
import io.murad.modern.ecommerce.repository.OrderItemRepository;
import io.murad.modern.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private  OrderItemService orderItemService;

    private String baseUrl = "http://localhost:8080/";

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;


    public Order saveOrder(PlaceOrderDto orderDto, User user, String sessionID) {
        Order order = getOrderFromDto(orderDto, user, sessionID);
        return orderRepository.save(order);
    }

    private Order getOrderFromDto(PlaceOrderDto orderDto, User user, String sessionID) {
        return new Order(orderDto, user, sessionID);
    }

    public List<Order> listOrders(User user) {
        List<Order> orderList = orderRepository.findAllByUserOrderByCreatedDateDesc(user);
        return orderList;
    }

    public Order getOrder(Long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }

    public void checkOut(User user, String sessionId) {
        CartDto cartDto = cartService.listCartItems(user);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setUser(user);
        placeOrderDto.setTotalPrice(cartDto.getTotalPrice());

        Order newOrder = saveOrder(placeOrderDto, user, sessionId);
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            OrderItem orderItem = new OrderItem(
                    newOrder,
                    cartItemDto.getProduct(),
                    cartItemDto.getQuantity(),
                    cartItemDto.getProduct().getProductPrice()
            );
            orderItemService.addOrderedProducts(orderItem);
        }
        cartService.deleteUserCartItem(user);
    }

    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(String.valueOf(Currency.USD))
                .setUnitAmount(((long) checkoutItemDto.getPrice()) * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItemDto))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        String successUrl = baseUrl + "payment/success";
        String failureUrl = baseUrl + "payment/failure";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<SessionCreateParams.LineItem>();
        for(CheckoutItemDto checkoutItemDto: checkoutItemDtoList){
        sessionItemList.add(createSessionLineItem(checkoutItemDto));
        }
//        checkoutItemDtoList.forEach(checkoutItemDto -> sessionItemList.add(createSessionLineItem(checkoutItemDto)));

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .addAllLineItem(sessionItemList)
                .setSuccessUrl(successUrl)
                .build();
        return Session.create(params);
    }
}
