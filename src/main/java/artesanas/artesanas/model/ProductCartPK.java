package artesanas.artesanas.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductCartPK  implements Serializable{
    private Cart cart;
    private Product product;

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof ProductCartPK productCartPK))
            return false;
        return cart.getIdCart() == productCartPK.cart.getIdCart() && Objects.equals(product, productCartPK.cart);
    }

    @Override
    public int hashCode(){
        return Objects.hash(cart, product);
    }

    public Product getProduct(){
        return product;
    }

    public Cart getCart(){
        return cart;
    }
}
