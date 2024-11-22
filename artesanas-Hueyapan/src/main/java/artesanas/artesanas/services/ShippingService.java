package artesanas.artesanas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Shipping;
import artesanas.artesanas.repository.ShippingRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

@Service
@Transactional
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;

    public List<Shipping> getAll() {
        return shippingRepository.findAll();
    }

    //paginacion
    public List<Shipping> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Shipping> shipping = shippingRepository.findAll(pageReq);
        return shipping.getContent();
    }

    public void save(Shipping shipping){
        shippingRepository.save(shipping);
    }

    public Shipping getIdShipping(Long idShipping){
        return shippingRepository.findById(idShipping).get();
    }

    public void delete (Long idShipping){
        shippingRepository.deleteById(idShipping);
    }

    //numero de segumiento
    public List<Shipping> getShippingByTrackingNumber(String trackingNumber) {
        return shippingRepository.getShippingByTrackingNumberJPQL(trackingNumber);
    }
    

}
