package artesanas.artesanas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductCartDTO {
    private Long cartId;
    private Long productId;

    @NotNull(message = "Quantity of products must not be null")
    @Min(value = 1, message = "Quantity of products must be at least 1")
    private Long quantityProducts;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantityProducts() {
        return quantityProducts;
    }

    public void setQuantityProducts(Long quantityProducts) {
        this.quantityProducts = quantityProducts;
    }

}