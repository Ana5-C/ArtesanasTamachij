package artesanas.artesanas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import artesanas.artesanas.model.Shopping;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    // Fecha
    @Query(value = "SELECT * FROM shopping WHERE date = :date", nativeQuery = true)
    List<Shopping> getShoppingByDate(@Param("date") String date);

    @Query(value = "SELECT * FROM shopping WHERE phone_number = :phoneNumber", nativeQuery = true)
    List<Shopping> getShoppingByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = "SELECT s FROM Shopping s WHERE s.date = :date")
    List<Shopping> getShoppingByDateJPQL(@Param("date") String date);

    @Query(value = "SELECT s FROM Shopping s WHERE s.phoneNumber = :phoneNumber")
    List<Shopping> getShoppingByPhoneNumberJPQL(@Param("phoneNumber") String phoneNumber);

}
