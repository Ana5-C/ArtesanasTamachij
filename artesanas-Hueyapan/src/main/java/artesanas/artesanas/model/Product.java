package artesanas.artesanas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "nombre")
    @JsonProperty("nombre")
    private String name;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 1000, message = "The content must be at most 1000")
    @Column(name = "descripcion")
    @JsonProperty("descripción")
    private String description;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    @Column(name = "precio")
    @JsonProperty("precio")
    private double price;

    @NotNull(message = "Stock must not be null")
    @Min(value = 1, message = "Stock must be zero")
    @Column(name = "stock")
    @JsonProperty("stock")
    private Long stock;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory")
    @JsonBackReference
    private Category category;

    public Product() {
    }
}
