package artesanas.artesanas.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @NotNull(message = "Total must not be null")
    @Min(value = 1, message = "Total must be at least 0")
    @Column(name = "total")
    @JsonProperty("total")
    private Integer total;

    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date must be in the past or present")
    @Column(name = "fecha")
    @JsonProperty("fecha")
    private Date date;

    @NotBlank(message = "Payment method must not be blank")
    @Size(min = 1, max = 30, message = "Payment method must be at most 30 characters")
    @Column(name = "metodoPago")
    @JsonProperty("metodoPago")
    private String paymentMethod;

}
