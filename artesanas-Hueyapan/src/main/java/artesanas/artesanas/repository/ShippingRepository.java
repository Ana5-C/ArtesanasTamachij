package artesanas.artesanas.repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import artesanas.artesanas.model.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    //numero de seguimiento
    @Query(value = "SELECT * FROM shipping WHERE tracking_number = :trackingNumber", nativeQuery = true)
    List<Shipping> getShippingByTrackingNumber(@Param("trackingNumber") String trackingNumber);

    @Query(value = "SELECT s FROM Shipping s WHERE s.trackingNumber = :trackingNumber")
    List<Shipping> getShippingByTrackingNumberJPQL(@Param("trackingNumber") String trackingNumber);

}
