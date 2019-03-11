package pl.coderslab.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String name;

    @Column(length = 100)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String help;

    @Column(length = 255)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 255, message = "Maksymalnie 255 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String address;

    @Email(message = "Wprowadź prawidłowy adres email")
    @Pattern(regexp = "^[a-zA-Z0-9]+[._-]*[a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-z]+)+([.][a-z]+)?$", message = "Wprowadź prawidłowy adres email", groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String email;

    @Column(length = 20)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 20, message = "Maksymalnie 20 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String phone;

    @Column(length = 50)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 50, message = "Maksymalnie 50 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String location;


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

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "institution",fetch = FetchType.EAGER)
    private List<Gift> giftList=new ArrayList<>();

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", help='" + help + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}


