//package io.murad.modern.ecommerce.mapper;
//
//import io.murad.modern.ecommerce.database.model.Order;
//import io.murad.modern.ecommerce.database.model.OrderItem;
//import io.murad.modern.ecommerce.database.model.User;
//import io.murad.modern.ecommerce.dto.OrderDto;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import javax.persistence.Transient;
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public abstract class OrderMapper {
//
//
//
//    @Mapping(target = "user", source = "user")
//    @Mapping(target = "totalPrice", expression = "java(getTotalOrderPrice())")
//    @Mapping(target = "orderItems", ignore = true)
//    @Mapping(target = "id", source = "orderDto.id")
//    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
//    public abstract Order mapToOrder(OrderDto orderDto,User user);
//
//    @Mapping(target = "userId", expression = "java(order.getUser().getId())")
//    public abstract OrderDto mapOrderToDto(Order order);
//
//    @Transient
//    public Double getTotalOrderPrice() {
//        Order order = new Order();
//        double sum = 0D;
//        List<OrderItem> orderProductItems = order.getOrderItems();
//        for (OrderItem orderItem : orderProductItems) {
//            sum += orderItem.getTotalPrice();
//        }
//        return sum;
//    }
//}
