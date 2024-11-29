package artesanas.artesanas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import artesanas.artesanas.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM category WHERE nombre = :name", nativeQuery = true)
    List<Category> getCategoriesByNameNative(@Param("name") String name);

    // Consulta JPQL para buscar clientes por nombre
    @Query(value = "SELECT c FROM Category c WHERE c.name = :name")
    List<Category> getCategoriesByNameJPQL(@Param("name") String name);
}
