package artesanas.artesanas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Product;
import artesanas.artesanas.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    
    public List<Product> getAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product getByIdProduct(Long idProduct){
        return productRepository.findById(idProduct).get();
    }

    public void delete(Long idProduct){
        productRepository.deleteById(idProduct);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.getProductsByNameJPQL(name); // Usar JPQL
    }

    public List<Product> getProductsByDescription(String description) {
        return productRepository.getProductsByDescriptionJPQL(description); // Usar JPQL
    }

    public List<Product> getProductsByPrice(double price) {
        return productRepository.getProductsByPriceJPQL(price); // Usar JPQL
    }

    public List<Product> getProductsByStock(Long stock) {
        return productRepository.getProductsByStockJPQL(stock); // Usar JPQL
    }
}
