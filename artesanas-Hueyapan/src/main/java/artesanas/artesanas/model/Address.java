package artesanas.artesanas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    @Column(name = "ciudad")
    @JsonProperty("ciudad")
    private String city;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    @Column(name = "colonia")
    @JsonProperty("colonia")
    private String cologne;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    @Column(name = "municipio")
    @JsonProperty("municipio")
    private String municipality;

    @NotNull(message = "The number must not be null")
    @Min(value = 1, message = "The number must be at least 1")
    @Column(name = "numero")
    @JsonProperty("numero")
    private Integer number;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    @Column(name = "calle")
    @JsonProperty("calle")
    private String street;

    @NotNull(message = "The zip code must not be null")
    @Min(value = 1000, message = "The zip code must be at least 4 digits")
    @Max(value = 99999, message = "The zip code must be at most 5 digits")
    @Column(name = "codigoPostal")
    @JsonProperty("codigoPostal")
    private String zipCode;

}