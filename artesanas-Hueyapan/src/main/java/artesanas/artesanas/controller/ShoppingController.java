package artesanas.artesanas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import artesanas.artesanas.model.Shopping;
import artesanas.artesanas.services.ShoppingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shoppings")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Shoppings", description = "Provides methods for managing shoppings ")
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

    // paginacion
    @Operation(summary = "Get all shopping with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Shopping> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Shopping> shopping = shoppingService.getAll(page, pageSize);
        return shopping;
    }

    @Operation(summary = "Get a shopping by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shopping found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Shopping.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Shopping not found", content = @Content) })
    @GetMapping("/{idShopping}")
    public ResponseEntity<Shopping> getIdShopping(@PathVariable Long idShopping) {
        Shopping shopping = shoppingService.getIdShopping(idShopping);
        return new ResponseEntity<Shopping>(shopping, HttpStatus.OK);

    }

    @Operation(summary = "Register a new shopping")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shopping shopping) {
        shoppingService.save(shopping);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @Operation(summary = "Update a Shopping")
    @PutMapping("/{idShopping}")
    public ResponseEntity<?> update(@RequestBody Shopping shopping, @PathVariable Long idShopping) {
        Shopping auxShopping = shoppingService.getIdShopping(idShopping);
        shopping.setIdShopping(auxShopping.getIdShopping());
        shoppingService.save(shopping);
        return new ResponseEntity<String>("Update", HttpStatus.OK);
    }

    @Operation(summary = "Get all shopping records by date")
    @ApiResponse(responseCode = "200", description = "Found shopping records by date", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))))
    @GetMapping("/search/date/{date}")
    public List<Shopping> getShoppingByDate(@PathVariable String date) {
        return shoppingService.getShoppingByDate(date);
    }

    @Operation(summary = "Get all shopping records by phone number")
    @ApiResponse(responseCode = "200", description = "Found shopping records by phone number", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shopping.class))))
    @GetMapping("/search/{phoneNumber}")
    public List<Shopping> getShoppingByPhoneNumber(@PathVariable String phoneNumber) {
        return shoppingService.getShoppingByPhoneNumber(phoneNumber);
    }

}
