package artesanas.artesanas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import artesanas.artesanas.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
    // Consulta nativa para buscar productos por nombre
    @Query(value = "SELECT * FROM products WHERE name = :name", nativeQuery = true)
    List<Product> getProductsByNameNative(@Param("name") String name);

    // Consulta JPQL para buscar productos por nombre
    @Query(value = "SELECT p FROM Product p WHERE p.name = :name")
    List<Product> getProductsByNameJPQL(@Param("name") String name);

    // Consulta nativa para buscar productos por descripción
    @Query(value = "SELECT * FROM products WHERE description LIKE %:description%", nativeQuery = true)
    List<Product> getProductsByDescriptionNative(@Param("description") String description);

    // Consulta JPQL para buscar productos por descripción
    @Query(value = "SELECT p FROM Product p WHERE p.description LIKE %:description%")
    List<Product> getProductsByDescriptionJPQL(@Param("description") String description);

    // Consulta nativa para buscar productos por precio menor o igual
    @Query(value = "SELECT * FROM products WHERE price = :price", nativeQuery = true)
    List<Product> getProductsByPriceNative(@Param("price") double price);

    // Consulta JPQL para buscar productos por precio menor o igual
    @Query(value = "SELECT p FROM Product p WHERE p.price = :price")
    List<Product> getProductsByPriceJPQL(@Param("price") double price);

    // Consulta nativa para buscar productos por stock mayor o igual
    @Query(value = "SELECT * FROM products WHERE stock = :stock", nativeQuery = true)
    List<Product> getProductsByStockNative(@Param("stock") Long stock);

    // Consulta JPQL para buscar productos por stock mayor o igual
    @Query(value = "SELECT p FROM Product p WHERE p.stock = :stock")
    List<Product> getProductsByStockJPQL(@Param("stock") Long stock);
}
