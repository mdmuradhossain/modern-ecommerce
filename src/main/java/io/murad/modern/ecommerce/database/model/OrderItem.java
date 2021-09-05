package io.murad.modern.ecommerce.database.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class OrderItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int quantity;

    @NotNull
    private Double price;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderItem orderItem = (OrderItem) o;

        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return 12332767;
    }

    @Transient
    public Double getTotalPrice() {
        return product.getPrice() * getQuantity();
    }
}
