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

    @Size(min = 8, max = 30, message = "Hasło musi miec od 8 do 30 znaków", groups = RegistrationValidator.class)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\=])(?=.*[A-Z])(?!.*\\s).{8,}$", message = "Hasło musi zawierać małą literę, dużą literę, cyfrę, conajmniej jeden znak !@#$%^&*()_+-= ", groups = {RegistrationValidator.class, EditValidator.class})
    private String password;

    @Transient
    @Size(min = 8, max = 30, message = "Hasło musi miec od 8 do 30 znaków", groups = RegistrationValidator.class)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\=])(?=.*[A-Z])(?!.*\\s).{8,}$", message = "Hasło musi zawierać małą literę, dużą literę, cyfrę, conajmniej jeden znak !@#$%^&*()_+-= ", groups = {RegistrationValidator.class, EditValidator.class})
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

    @Column(name = "can_log_in")
    private boolean canLogin;

    @Column(length = 10)
    private int token;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Gift> giftList = new ArrayList<>();

    // user (id, login, password, firstName, lastName, email, admin)


    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public boolean isCanLogin() {
        return canLogin;
    }

    public void setCanLogin(boolean canLogin) {
        this.canLogin = canLogin;
    }

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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", superUser=" + superUser +
                ", canLogin=" + canLogin +
                '}';
    }
}
