package artesanas.artesanas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import artesanas.artesanas.model.ProductCart;
import artesanas.artesanas.services.ProductCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/productsCarts")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Product Cart", description = "provides methods for productcart")
public class ProductCartController {
    @Autowired
    private ProductCartService productCartService;

    @Operation(summary = "Get all product carts")
    @ApiResponse(responseCode = "200", description = "Found product carts", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductCart.class)))
    })
    @GetMapping
    public List<ProductCart> getAll() {
        return productCartService.getAll();
    }
}
