package io.murad.modern.ecommerce.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.murad.modern.ecommerce.dto.PlaceOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "product_order")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant createdDate;

    private Double totalPrice;

    private String sessionId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Order(PlaceOrderDto orderDto, User user, String sessionID) {
        this.user = user;
        this.createdDate = Instant.now();
        this.totalPrice = orderDto.getTotalPrice();
        this.sessionId = sessionId;
    }
}
