package artesanas.artesanas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Shopping;
import artesanas.artesanas.repository.ShoppingRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

     //Paginacion
    public List<Shopping> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Shopping> shopping = shoppingRepository.findAll(pageReq);
        return shopping.getContent();
    }
    
    public List<Shopping> getAll(){
        return shoppingRepository.findAll();
    }

    public void save (Shopping shopping){
        shoppingRepository.save(shopping);
    }

    public Shopping getIdShopping(Long idShopping){
        return shoppingRepository.findById(idShopping).get();
    }

    public List<Shopping> getShoppingByDate(String date) {
        return shoppingRepository.getShoppingByDateJPQL(date);
    }
    
    public List<Shopping> getShoppingByPhoneNumber(String phoneNumber) {
        return shoppingRepository.getShoppingByPhoneNumberJPQL(phoneNumber);
    }
    
}
