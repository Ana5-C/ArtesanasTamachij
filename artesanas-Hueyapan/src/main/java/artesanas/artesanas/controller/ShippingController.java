package artesanas.artesanas.controller;

import java.util.List;
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

import artesanas.artesanas.model.Shipping;
import artesanas.artesanas.services.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shippings")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Shippings", description = "Provides methods for managing shipping")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

     // Consulta
   /*  @Operation(summary = "Get all shipping types")
    @ApiResponse(responseCode = "100", description = "Found shipping types", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shipping.class)))
    })
    @GetMapping
    public List<Shipping> getAll() {
        return shippingService.getAll();
    }  */

    // paginacion
    @Operation(summary = "Get all shipping with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Shipping> getAllPagginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Shipping> shipping = shippingService.getAll(page, pageSize);
        return shipping;
    }

    // Consulta por ID
    @Operation(summary = "Get a shipping by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shipping found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Shipping.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content) })
    @GetMapping("/{idShipping}")
    public ResponseEntity<Shipping> getIdShipping(@PathVariable Long idShipping) {
        Shipping shipping = shippingService.getIdShipping(idShipping);
        return new ResponseEntity<Shipping>(shipping, HttpStatus.OK);
    }

    // Registra
    @Operation(summary = "Register a new shipping")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shipping shipping) {
        shippingService.save(shipping);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    // Actualiza
    @Operation(summary = "Update a Shipping")
    @PutMapping("/{idShipping}")
    public ResponseEntity<?> update(@RequestBody Shipping shipping, @PathVariable Long idShipping) {
        Shipping auxShipping = shippingService.getIdShipping(idShipping);
        shipping.setIdShipping(auxShipping.getIdShipping());
        shippingService.save(shipping);
        return new ResponseEntity<String>("Update", HttpStatus.OK);
    }

    // Elimina
    @Operation(summary = "Delete a shipping")
    @DeleteMapping("/{idShipping}")
    public ResponseEntity<?> delete(@PathVariable Long idShipping) {
        shippingService.delete(idShipping);
        return new ResponseEntity<>("Delete", HttpStatus.OK);
    }

    // numero de seguimiento
    @Operation(summary = "Get all shipping records by tracking number")
    @ApiResponse(responseCode = "200", description = "Found shipping records", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shipping.class))) })
    @GetMapping("/search/{trackingNumber}")
    public List<Shipping> getShippingByTrackingNumber(@PathVariable String trackingNumber) {
        return shippingService.getShippingByTrackingNumber(trackingNumber);
    }

}
