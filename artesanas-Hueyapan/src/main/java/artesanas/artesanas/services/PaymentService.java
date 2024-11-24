package artesanas.artesanas.services;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Payment;
import artesanas.artesanas.repository.PaymentRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    //Paginacion
    public List<Payment> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Payment> payment = paymentRepository.findAll(pageReq);
        return payment.getContent();
    }

    public void save(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment getIdPayment(Long idPayment){
        return paymentRepository.findById(idPayment).get();
    }

    public void delete(Long idPayment){
        paymentRepository.deleteById(idPayment);
    }

    //Metodo de pago
    public List<Payment> getPaymentByPaymentMethod(String paymentMethod) {
        return paymentRepository.getPaymentByPaymentMethodJPQL(paymentMethod);
    }
}
