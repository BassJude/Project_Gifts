package pl.coderslab.model;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "gifts")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    @Size(max = 255, message = "Maksymalnie 255 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    private String decsription;  // to give Away from user

    @Column(length = 255, name = "courier_decsription")
    @Size(max = 255, message = "Maksymalnie 255 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String courierDecsription;

    @Min(value = 1, message = "Ilość worków musi być liczbą")
    @Max(value = 100, message = "Maksymalnie 100 worków")
    private int bags;

    @Column(length = 100)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String city;

    @Column(length = 10, name = "zip_code")
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 10, message = "Maksymalnie 10 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String zipCode;

    @Column(length = 100)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 100, message = "Maksymalnie 100 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String street;

    @Column(length = 50, name = "number_of_home")
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 50, message = "Maksymalnie 50 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String homeNumber;

    @Column(length = 20)
    @NotBlank(groups = {RegistrationValidator.class, EditValidator.class})
    @Size(max = 20, message = "Maksymalnie 20 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String phone;

    @Column(length = 30)
    private String status;


    @Column(name = "pickup_time")
    @Pattern(regexp = "\\d{4}-[01]\\d-[0-3]\\d\\s[0-2]\\d:[0-5]\\d", message = "Wpisz według formatu: yyyy-MM-dd HH:mm", groups = {RegistrationValidator.class})
    private LocalDateTime pickUpTime; // pick up by courier from user

    @Column(name = "send_time")
    private LocalDateTime sendTime; // send to organisastion

    //  opcjonalnie @ManyToOne(cascade = CascadeType.Merge)
    @ManyToOne
    private User user;

    //  opcjonalnie @ManyToOne(cascade = CascadeType.Merge)
    @ManyToOne
    private Institution institution;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDecsription() {
        return decsription;
    }

    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    public String getCourierDecsription() {
        return courierDecsription;
    }

    public void setCourierDecsription(String courierDecsription) {
        this.courierDecsription = courierDecsription;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", decsription='" + decsription + '\'' +
                ", courierDecsription='" + courierDecsription + '\'' +
                ", bags=" + bags +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
