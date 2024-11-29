package artesanas.artesanas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "productCart")
@IdClass(ProductCartPK.class)
public class ProductCart {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCart")
    @JsonBackReference
    private Cart cart;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", referencedColumnName = "idProduct")
    private Product product;

    @NotNull(message = "Quantity of products must not be null")
    @Min(value = 1, message = "Quantity of products must be at least 1")
    @Column(name = "cantidadProductos")
    @JsonProperty("cantidadProductos")
    private Long quantityProducts;

}