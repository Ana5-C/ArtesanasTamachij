package artesanas.artesanas.dto;

import java.sql.Date;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class PaymentRequestDTO {

    private Long idPayment;

    @NotNull(message = "Total must not be null")
    @Min(value = 1, message = "Total must be at least 0")
    private Integer total;

    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date must be in the past or present")
    private Date date;

    @NotBlank(message = "Payment method must not be blank")
    @Size(min = 1, max = 30, message = "Payment method must be at most 30 characters")
    private String paymentMethod;

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


}
