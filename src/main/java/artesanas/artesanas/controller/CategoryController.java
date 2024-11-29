package artesanas.artesanas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import artesanas.artesanas.model.Category;
import artesanas.artesanas.services.CategoryService;

import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT })
@Tag(name = "Categories", description = "provides methods for categories ")
public class CategoryController {
        @Autowired
        private CategoryService categoryService;

        // paginacion
        @Operation(summary = "Get all categories with pagination")
        @GetMapping(value = "pagination", params = { "page", "pageSize" })
        public List<Category> getAllPaginated(
                        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                        @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

                List<Category> categories = categoryService.getAll(page, pageSize);
                return categories;
        }

        @Operation(summary = "Get a category by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "category found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
                        @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
                        @ApiResponse(responseCode = "404", description = "category not found", content = @Content) })
        @GetMapping("/{idCategory}")
        public ResponseEntity<Category> findByIdCategory(@PathVariable Long idCategory) {
                // Category category = categoryService.getByIdCategory(idCategory);
                // return new ResponseEntity<Category>(category, HttpStatus.OK);
                return new ResponseEntity<Category>(categoryService.getByIdCategory(idCategory), HttpStatus.OK);
        }

        @Operation(summary = "Register a new category")
        @PostMapping
        public ResponseEntity<?> create(@RequestBody Category category) {
                categoryService.save(category);
                return new ResponseEntity<String>("Saved", HttpStatus.OK);
        }

        @Operation(summary = "Update a category")
        @PutMapping("/{idCategory}")
        public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Long idCategory) {
                Category auxCategory = categoryService.getByIdCategory(idCategory);
                category.setIdCategory(auxCategory.getIdCategory());
                categoryService.save(category);
                return new ResponseEntity<String>("Updated", HttpStatus.OK);
        }

        @Operation(summary = "Delete a category")
        @DeleteMapping("/{idCategory}")
        public ResponseEntity<?> delete(@PathVariable Long idCategory) {
                categoryService.delete(idCategory);
                return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        }

        @Operation(summary = "Get categories by name")
        @ApiResponse(responseCode = "200", description = "Found categories by name", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class)))
        })
        @GetMapping("/name/{name}")
        public List<Category> getCategoriesByName(@PathVariable String name) {
                return categoryService.getCategoryByName(name);
        }
}
