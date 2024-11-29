package artesanas.artesanas.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Cart;
import artesanas.artesanas.repository.CartRepository;
import jakarta.transaction.Transactional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public List<Cart> getAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Cart> cartPage = cartRepository.findAll(pageRequest);
        return cartPage.getContent();
    }


    @Transactional
    public Cart save (Cart cart){
        return cartRepository.save(cart);
    }
    /*public void save(Cart cart) {
        cartRepository.save(cart);
    }*/

    public Cart getByIdCart(Long idCart) {
        return cartRepository.findById(idCart).get();
    }

    public void delete(Long idCart) {
        cartRepository.deleteById(idCart);
    }

    public List<Cart> getCartByDate(String dateCreated){
        return cartRepository.getCartByDateJPQL(dateCreated);
    }
}
