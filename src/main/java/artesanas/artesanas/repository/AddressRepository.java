package artesanas.artesanas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import artesanas.artesanas.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    //city
    @Query(value = "SELECT * FROM address WHERE city = :city", nativeQuery = true)
    List<Address> getAddressByCity(@Param("city") String city);

    @Query(value = "SELECT a FROM Address a WHERE a.city = :city")
    List<Address> getAddressByCityJPQL(@Param("city") String city);



}