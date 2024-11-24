package artesanas.artesanas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import artesanas.artesanas.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

//Metodo de pago
    @Query(value = "SELECT * FROM payment WHERE paymentMethod = :paymentMethod", nativeQuery = true)
    List<Payment> getPaymentByPaymentMethod(@Param("paymentMethod") String paymentMethod);

    @Query(value = "SELECT p FROM Payment p WHERE p.paymentMethod = :paymentMethod")
    List<Payment> getPaymentByPaymentMethodJPQL(@Param("paymentMethod") String paymentMethod);


}
