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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import artesanas.artesanas.model.Address;
import artesanas.artesanas.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Addresses", description = "Provides methods for managing address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    // Consulta
    /* @Operation(summary = "Get all address types")
    @ApiResponse(responseCode = "200", description = "Found address types", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Address.class)))
    })
    @GetMapping
    public List<Address> getAll() {
        return addressService.getAll();
    }  */

    // Paginacion
    @Operation(summary = "Get all address with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Address> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Address> address = addressService.getAll(page, pageSize);
        return address;
    }

    // Buscar por ID
    @Operation(summary = "Get a address by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Address.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content) })
    @GetMapping("/{idAddress}")
    public ResponseEntity<Address> getIdAddress(@PathVariable Long idAddress) {
        return new ResponseEntity<Address>(addressService.getIdAddress(idAddress), HttpStatus.OK);
    }
    /*
     * public ResponseEntity<Address> getIdAddress(@PathVariable Long idAddress) {
     * Address address = addressService.getIdAddress(idAddress);
     * return new ResponseEntity<Address>(address, HttpStatus.OK);
     * }
     */

    // Registra
    @Operation(summary = "Register a new address")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Address address) {
        addressService.save(address);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    // Actualiza
    @Operation(summary = "Update a Address")
    @PutMapping("/{idAddress}")
    public ResponseEntity<?> update(@RequestBody Address address, @PathVariable Long idAddress) {
        Address auxAddress = addressService.getIdAddress(idAddress);
        address.setIdAddress(auxAddress.getIdAddress());
        addressService.save(address);
        return new ResponseEntity<>("Update", HttpStatus.OK);
    }

    // Elimina
    @Operation(summary = "Delete a Address")
    @DeleteMapping("/{idAddress}")
    public ResponseEntity<?> delete(@PathVariable Long idAddress) {
        addressService.delete(idAddress);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

    // Buscar por la ciudad
    @Operation(summary = "Get all addresses by city")
    @ApiResponse(responseCode = "200", description = "Found addresses", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Address.class))) })
    @GetMapping("/search/{city}")
    public List<Address> getAddressByCity(@PathVariable String city) {
        return addressService.getAddressByCity(city);
    }

}