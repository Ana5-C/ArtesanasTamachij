package artesanas.artesanas.dto;

import java.util.List;

import artesanas.artesanas.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {

    private Long id;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 10, message = "The content must be at most 10")
    private String name;

    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    
}
