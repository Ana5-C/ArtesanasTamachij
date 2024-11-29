package artesanas.artesanas.dto;

import java.sql.Date;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class ShoppingRequestDTO {
    private Long idShopping;

    @NotNull(message = "Amount must not be null")
    @Min(value = 1, message = "Amount must be at least 1")
    private Integer amount;

    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date must be in the past or present")
    private Date date;

    @NotNull(message = "Phone number must not be null")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits")
    private Integer phoneNumber;

    @NotNull(message = "Total to pay must not be null")
    @Min(value = 1, message = "Total to pay must be at least 1")
    private Integer total;

    private Long paymentId;
    private Long shippingId;
    private Long cartId;

    public Long getIdShopping() {
        return idShopping;
    }

    public void setIdShopping(Long idShopping) {
        this.idShopping = idShopping;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    
}
