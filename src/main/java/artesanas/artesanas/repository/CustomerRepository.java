package artesanas.artesanas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import artesanas.artesanas.model.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    // Consulta nativa para buscar clientes por nombre
    @Query(value = "SELECT * FROM customers WHERE nombre = :name", nativeQuery = true)
    List<Customer> getCustomersByNameNative(@Param("name") String name);

    // Consulta JPQL para buscar clientes por nombre
    @Query(value = "SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> getCustomersByNameJPQL(@Param("name") String name);

    // Consulta nativa para buscar clientes por correo electrónico
    @Query(value = "SELECT * FROM customers WHERE correo = :email", nativeQuery = true)
    Optional<Customer> getCustomerByEmailNative(@Param("email") String email);

    // Consulta JPQL para buscar clientes por correo electrónico
    @Query(value = "SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> getCustomerByEmailJPQL(@Param("email") String email);

    // Consulta nativa para eliminar un cliente por nombre de usuario
    @Query(value = "SELECT * FROM customers WHERE nombreUsuario = :userName", nativeQuery = true)
    List<Customer> getCustomerByUserNameNative(@Param("userName") String userName);

    // Consulta JPQL para eliminar un cliente por nombre de usuario
    @Query(value = "SELECT c FROM Customer c WHERE c.userName = :userName")
    List<Customer> getCustomerByUserNameJPQL(@Param("userName") String userName);
}