package artesanas.artesanas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO {

    private Long id;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    private String name;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    private String lastName;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    private String email;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 16, message = "The content must be at most 16")
    private String userName;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 16, message = "The content must be at most 16")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    
}
