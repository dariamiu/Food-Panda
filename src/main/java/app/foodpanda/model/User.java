package app.foodpanda.model;

import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
@Table(name ="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_user;

    @NotEmpty(message = "The username field is empty")
    @Column
    private String username;


    @NotEmpty(message = "The password field is empty")
    @Column
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
