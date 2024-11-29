package artesanas.artesanas.controller;

import java.util.List;
import java.util.Optional;

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

import artesanas.artesanas.model.Customer;
import artesanas.artesanas.services.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Customers", description = "provides methods for customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /* @Operation(summary = "Get all customers")
    @ApiResponse(responseCode = "200", description = "Found customers", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Customer.class)))
    })
    @GetMapping()
    public List<Customer> getAll() {
        return customerService.getAll();
    } */

    @Operation(summary = "Get all customers with pagination")
    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    public List<Customer> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        List<Customer> customers = customerService.getAll(page, pageSize);
        return customers;
    }

    @Operation(summary = "Get a customer by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content) })
    @GetMapping("/{idCustomer}")
    public ResponseEntity<Customer> getIdCustomer(@PathVariable Long idCustomer) {
        return new ResponseEntity<Customer>(customerService.getIdCustomer(idCustomer), HttpStatus.OK);
    }
    /*
     * public ResponseEntity<Customer> getIdClient(@PathVariable Long idCustomer) {
     * Customer customer = customerService.getIdCustomer(idCustomer);
     * return new ResponseEntity<Customer>(customer, HttpStatus.OK);
     * }
     */

    @Operation(summary = "Register a new customer")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<String>("saved", HttpStatus.OK);
    }

    @Operation(summary = "Update a customer")
    @PutMapping("/{idCustomer}")
    public ResponseEntity<?> update(@RequestBody Customer customer, @PathVariable Long idCustomer) {
        Customer auxCustomer = customerService.getIdCustomer(idCustomer);
        customer.setIdCustomer(auxCustomer.getIdCustomer());
        customerService.save(customer);
        return new ResponseEntity<String>("Updated", HttpStatus.OK);
    }

    @Operation(summary = "Delete a customer")
    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<?> delete(@PathVariable Long idCustomer) {
        customerService.delete(idCustomer);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

    @Operation(summary = "Get customers by name")
    @ApiResponse(responseCode = "200", description = "Found customers by name", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Customer.class)))
    })
    @GetMapping("/name/{name}")
    public List<Customer> getCustomersByName(@PathVariable String name) {
        return customerService.getCustomersByName(name);
    }

    @Operation(summary = "Get customer by email")
    @ApiResponse(responseCode = "200", description = "Found customer by email", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
    })
    @GetMapping("/email/{email}")
    public Optional<Customer> getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @Operation(summary = "Get customer by username")
    @ApiResponse(responseCode = "200", description = "Found customer by userName", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
    })
    @GetMapping("/username/{userName}")
    public List<Customer> getCustomersByUserName(@PathVariable String user) {
        return customerService.getCustomersByName(user);
    }

}
