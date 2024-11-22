package artesanas.artesanas.controller;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

import artesanas.artesanas.dto.CartRequestDTO;
import artesanas.artesanas.dto.ProductCartDTO;
import artesanas.artesanas.model.Cart;
import artesanas.artesanas.model.Product;
import artesanas.artesanas.model.ProductCart;
import artesanas.artesanas.services.CartService;
import artesanas.artesanas.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "Carts", description = "provides methods for carts ")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    // encuentra todos
    @Operation(summary = "Get all cart")
    @ApiResponse(responseCode = "200", description = "Found customer", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cart.class)))
    })
    @GetMapping
    public List<Cart> getAll() {
        return cartService.getAll();
    }

    // paginacion
    @Operation(summary = "Get all carts with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Cart> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        List<Cart> carts = cartService.getAll(page, pageSize);
        return carts;
    }

    // encuentra por ID
    @Operation(summary = "Get a cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart not found", content = @Content) })
    @GetMapping("/{idCart}")
    public ResponseEntity<Cart> getByIdCart(@PathVariable Long idCart) {
        // Cart cart = cartService.getByIdCart(idCart);
        // return new ResponseEntity<Cart>(cart, HttpStatus.OK);
        return new ResponseEntity<Cart>(cartService.getByIdCart(idCart), HttpStatus.OK);
    }

    // Crea un carrito
    @Operation(summary = "Register a new cart")
    @ApiResponse(responseCode = "201", description = "Registered cart", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) })
    @PostMapping
    public ResponseEntity<Cart> add(
            @RequestBody CartRequestDTO cartRequestDTO) {
        Cart savedCart = cartService.save(convertToEntity(cartRequestDTO));
        return new ResponseEntity<Cart>(savedCart, HttpStatus.CREATED);
    }

    public Cart convertToEntity(CartRequestDTO cartRequestDTO) {
        List<ProductCartDTO> productCartDTOs = cartRequestDTO.getProductCartDTOs();
        List<ProductCart> productCarts = productCartDTOs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        Cart cart = modelMapper.map(cartRequestDTO, Cart.class);
        cart.setProductsCart(productCarts);

        Iterator<ProductCartDTO> productCartDTOIterator = productCartDTOs.iterator();
        Iterator<ProductCart> productCartIterator = productCarts.iterator();

        while (productCartDTOIterator.hasNext()) {
            ProductCartDTO productCartDTO = productCartDTOIterator.next();
            ProductCart productCart = productCartIterator.next();
            Product managedProduct = productService.getByIdProduct(productCartDTO.getProductId());

            productCart.setProduct(managedProduct);
            productCart.setCart(cart);
        }
        return cart;
    }

    private ProductCart convertToDTO(ProductCartDTO productCartDTO) {
		return modelMapper.map(productCartDTO, ProductCart.class);
	}

    /*
     * public ResponseEntity<?> create(@RequestBody Cart cart) {
     * cartService.save(cart);
     * return new ResponseEntity<String>("saved", HttpStatus.OK);
     * }
     */

    // actualiza un carrito
    @Operation(summary = "Update a cart")
    @PutMapping("/{idCart}")
    public ResponseEntity<?> update(@RequestBody Cart cart, @PathVariable Long idCart) {
        Cart auxCart = cartService.getByIdCart(idCart);
        cart.setIdCart(auxCart.getIdCart());
        cartService.save(cart);
        return new ResponseEntity<String>("updated", HttpStatus.OK);
    }

    // elimina carrito
    @Operation(summary = "Delete a cart")
    @DeleteMapping("/{idCart}")
    public ResponseEntity<?> delete(@PathVariable Long idCart) {
        cartService.delete(idCart);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

    @Operation(summary = "Get carts by creation date")
    @ApiResponse(responseCode = "200", description = "Found a cart by creation date", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class))
    })
    @GetMapping("/dateCreated/{dateCreated}")
    public List<Cart> getCartByDate(@PathVariable String dateCreated) {
        return cartService.getCartByDate(dateCreated);
    }
}

