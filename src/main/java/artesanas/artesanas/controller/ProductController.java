package artesanas.artesanas.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import artesanas.artesanas.dto.ProductRequestDTO;
import artesanas.artesanas.model.Product;
import artesanas.artesanas.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,  RequestMethod.PUT })
@Tag(name = "Products", description = "provides methods for products ")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    // encuentra todos
    /*
     * @Operation(summary = "Get all product")
     * 
     * @GetMapping
     * public List<Product> getAll() {
     * return productService.getAll();
     * }
     */

    //Paginación
    @Operation(summary = "Get all products with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Product> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        List<Product> products = productService.getAll(page, pageSize);
        return products;
    }

    // encuentra por ID
    @Operation(summary = "Get a product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findByIdProduct(@PathVariable Long idProduct) {
        return new ResponseEntity<Product>(productService.getByIdProduct(idProduct), HttpStatus.OK);
    }

    // crea un producto
    @Operation(summary = "Register a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content) })
    @PostMapping
    public ResponseEntity<ProductRequestDTO> add(
            @RequestBody ProductRequestDTO productRequestDTO) {
        ProductRequestDTO savedProductRequestDTO = convertToDTO(
                productService.save(convertToEntity(productRequestDTO)));
        return new ResponseEntity<ProductRequestDTO>(savedProductRequestDTO, HttpStatus.CREATED);
    }

    public Product convertToEntity(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }

    private ProductRequestDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductRequestDTO.class);
    }

    // Actualiza un producto
    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found") })
    @PutMapping("/{idProduct}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long idProduct) {
        Product auxProduct = productService.getByIdProduct(idProduct);
        product.setIdProduct(auxProduct.getIdProduct());
        return new ResponseEntity<String>("Updated", HttpStatus.OK);
    }

    // Elimina un producto
    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found") })
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> delete(@PathVariable Long idProduct) {
        productService.delete(idProduct);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

    // Encuentra por nombre
    @Operation(summary = "Get products by name")
    @ApiResponse(responseCode = "200", description = "Found products by name", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @GetMapping("/name/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

    // Encuentra por descripción
    @Operation(summary = "Get products by description")
    @ApiResponse(responseCode = "200", description = "Found products by description", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @GetMapping("/description/{description}")
    public List<Product> getProductsByDescription(@PathVariable String description) {
        return productService.getProductsByDescription(description);
    }

    // Encuentra por precio
    @Operation(summary = "Get products by price")
    @ApiResponse(responseCode = "200", description = "Found products by price", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @GetMapping("/price/{price}")
    public List<Product> getProductsByPrice(@PathVariable double price) {
        return productService.getProductsByPrice(price);
    }

    // Encuentra por stock
    @Operation(summary = "Get products by stock")
    @ApiResponse(responseCode = "200", description = "Found products by stock", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @GetMapping("/stock/{stock}")
    public List<Product> getProductsByStock(@PathVariable Long stock) {
        return productService.getProductsByStock(stock);
    }
}
