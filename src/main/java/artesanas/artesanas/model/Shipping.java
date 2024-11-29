package artesanas.artesanas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShipping;
    
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character ")
    @Size(min = 1, max = 500, message = "The content must be at most 500 characteres, and has at least one character")
    @Column(name = "paqueteria")
    @JsonProperty("paqueteria")
    private String parcel;
   
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character ")
    @Size(min = 1, max = 30, message = "The content must be at most 500 characteres, and has at least one character")
    @Column(name = "numeroSeguimiento")
    @JsonProperty("numeroSeguimiento")
    private String trackingNumber;

    @OneToOne
    @JoinColumn(name = "id_address", referencedColumnName = "idAddress")
    private Address address;


    public Long getIdShipping() {
        return idShipping;
    }


    public void setIdShipping(Long idShipping) {
        this.idShipping = idShipping;
    }


    public String getParcel() {
        return parcel;
    }


    public void setParcel(String parcel) {
        this.parcel = parcel;
    }


    public String getTrackingNumber() {
        return trackingNumber;
    }


    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Shipping [idShipping=" + idShipping + ", parcel=" + parcel + ", trackingNumber=" + trackingNumber
                + ", address=" + address + "]";
    }

    
    
    
}
