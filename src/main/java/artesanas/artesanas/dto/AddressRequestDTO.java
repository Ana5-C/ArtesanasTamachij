package artesanas.artesanas.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressRequestDTO {

    private Long id;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    private String city;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    private String cologne;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    private String municipality;

    @NotNull(message = "The number must not be null")
    @Min(value = 1, message = "The number must be at least 1")
    private Integer number;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    private String street;

    @NotNull(message = "The zip code must not be null")
    @Min(value = 1000, message = "The zip code must be at least 4 digits")
    @Max(value = 99999, message = "The zip code must be at most 5 digits")
    private String zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCologne() {
        return cologne;
    }

    public void setCologne(String cologne) {
        this.cologne = cologne;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    
}
