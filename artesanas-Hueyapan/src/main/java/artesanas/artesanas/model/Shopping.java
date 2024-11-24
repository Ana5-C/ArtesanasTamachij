package artesanas.artesanas.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "shopping")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idShopping;

    @NotNull(message = "Amount must not be null")
    @Min(value = 1, message = "Amount must be at least 1")
    @Column(name = "cantidadProductos")
    @JsonProperty("cantidadProductos")
    private Integer amount;

    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date must be in the past or present")
    @Column(name = "fecha")
    @JsonProperty("fecha")
    private Date date;

    @NotNull(message = "Phone number must not be null")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be exactly 10 digits")
    @Column(name = "numeroTelefono")
    @JsonProperty("numeroTelefono")
    private Integer phoneNumber;

    @NotNull(message = "Total to pay must not be null")
    @Min(value = 1, message = "Total to pay must be at least 1")
    @Column(name = "totalPagar")
    @JsonProperty("totalPagar")
    private Integer total;

    @OneToOne
    @JoinColumn(name = "id_payment", referencedColumnName = "idPayment")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "id_shipping", referencedColumnName = "idShipping")
    private Shipping shipping;

    @OneToOne
    @JoinColumn(name = "id_cart", referencedColumnName = "idCart")
    private Cart cart;

}