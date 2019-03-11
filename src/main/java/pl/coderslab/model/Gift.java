package pl.coderslab.model;

import org.hibernate.validator.constraints.Email;
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
    private String decsription;

    @Column(length = 255, name = "courier_decsription")
    @Size(max = 255, message = "Maksymalnie 255 znaków", groups = {RegistrationValidator.class, EditValidator.class})
    private String courierDecsription;

    @Min(value = 1, message = "Ilość worków musi być liczbą")
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

    @Column(name = "pickup_time")
    private LocalDateTime pickUpTime;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    //  opcjonalnie @ManyToOne(cascade = CascadeType.Merge)
    @ManyToOne
    private User user;

    //  opcjonalnie @ManyToOne(cascade = CascadeType.Merge)
    @ManyToOne
    private Institution institution;


}
