package co.empathy.academy.searchAPI.models;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {

    @NotBlank(message = "ID is mandatory")
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    // standard constructors / toString
    public User(@NotNull String name, @NotNull String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + ", name=" + name + "]";
    }

}