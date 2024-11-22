package artesanas.artesanas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Category;
import artesanas.artesanas.repository.CategoryRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // Retrieve a category by its ID
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageRequest);
        return categoryPage.getContent();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category getByIdCategory(Long idCategory) {
        return categoryRepository.findById(idCategory).get();
    }

    public void delete(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }

    public List<Category> getCategoryByName(String name) {
        return categoryRepository.getCategoriesByNameJPQL(name);
    }
}
