package pl.coderslab.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.utils.BCrypt;
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(length = 50)
//    @NotBlank(groups = {RegistrationValidator.class}, message = "Podaj login")
//    @Size(max = 50, message = "Login moze mieć maksymalnie 50 znaków", groups = {RegistrationValidator.class})
//    private String login;

    @Email(message = "Wprowadź prawidłowy adres email")
    @Pattern(regexp = "^[a-zA-Z0-9]+[._-]*[a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-z]+)+([.][a-z]+)?$", message = "Wprowadź prawidłowy adres email", groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String email;

    @Size(min = 5, max = 30, message = "Hasło musi miec od 5 do 30 znaków", groups = RegistrationValidator.class)
    @NotBlank(groups = RegistrationValidator.class)
    private String password;

    @Transient
    @Size(min = 5, max = 30, message = "Hasło musi miec od 5 do 30 znaków", groups = RegistrationValidator.class)
    @NotBlank(groups = RegistrationValidator.class)
    private String password2;

    @Column(length = 100)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String firstName;

    @Column(length = 100)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String lastName;


    @Column(name = "admin")
    private boolean superUser;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Gift> giftList=new ArrayList<>();

    // user (id, login, password, firstName, lastName, email, admin)

    public void setPasswordHash(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public boolean isSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
//                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
