package artesanas.artesanas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import artesanas.artesanas.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value= "SELECT * FROM cart WHERE fecha = :dateCreated", nativeQuery = true)
    List<Cart> getCartByDateNative(@Param("dateCreated") String dateCreated);

    @Query(value = "SELECT c FROM Cart c WHERE c.dateCreated = :dateCreated")
    List<Cart> getCartByDateJPQL(@Param("dateCreated") String dateCreated);
}
