package artesanas.artesanas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import artesanas.artesanas.model.ProductCart;
import artesanas.artesanas.model.ProductCartPK;


@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, ProductCartPK>{
}