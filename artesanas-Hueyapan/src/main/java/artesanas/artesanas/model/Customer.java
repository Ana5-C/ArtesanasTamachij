package artesanas.artesanas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//a class representing a data entity
@Entity
@Table(name = "customers")
public class Customer {

    //unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //attributes
    private Long idCustomer;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "nombre")
    @JsonProperty("nombre")
    private String name;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "apellidos")
    @JsonProperty("apellidos")
    private String lastName;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "correo")
    @JsonProperty("correo")
    private String email;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 16, message = "The content must be at most 16")
    @Column(name = "nombreUsuario")
    @JsonProperty("nombreUsuario")
    private String userName;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 16, message = "The content must be at most 16")
    @Column(name = "contrasena")
    @JsonProperty("contrasena")
    private String password;

    // Methods Getters y Setters
    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public Cart getCart() {
    //     return cart;
    // }

    // public void setCart(Cart cart) {
    //     this.cart = cart;
    // }

    // returns a string representation of the object
    @Override
    public String toString() {
        return "Customer [idCustomer=" + idCustomer + ", name=" + name + ", lastName=" + lastName + ", email=" + email
                + ", userName=" + userName + ", password=" + password + "]";
    }

}

