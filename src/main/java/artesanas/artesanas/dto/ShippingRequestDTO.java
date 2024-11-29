package artesanas.artesanas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ShippingRequestDTO {
    private Long idShipping;
    
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character ")
    @Size(min = 1, max = 500, message = "The content must be at most 500 characteres, and has at least one character")
    private String parcel;
   
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character ")
    @Size(min = 1, max = 30, message = "The content must be at most 500 characteres, and has at least one character")
    private String trackingNumber;

    private Long addressId;

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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    
}
