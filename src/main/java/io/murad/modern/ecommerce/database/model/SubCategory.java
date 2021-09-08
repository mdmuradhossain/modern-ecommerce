package io.murad.modern.ecommerce.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity()
@Table(name = "sub_category")
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subCategoryName;

    private String description;

    @ManyToOne
    @JoinColumn(name="sub_category_id",referencedColumnName="id")
    private Category category;
}
