package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.model.Gift;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.repository.GiftRepository;
import pl.coderslab.repository.InstitutionRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class GiftService {

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private UserSession userSession;

    @Autowired
    private InstitutionRepository institutionRepository;

    public void save(Gift gift) {
        giftRepository.save(gift);
    }

    public void update(Gift gift) {
        giftRepository.save(gift);
    }

    public void deleteById(Long id) {
        giftRepository.delete(id);
    }

    public void delete(Gift gift) {
        giftRepository.delete(gift);
    }

    public Gift findById(Long id) {
        return giftRepository.findOne(id);
    }

    public List<Gift> findAll() {
        return giftRepository.findAll();
    }


    public List<Gift> findUserGifts(User user) {
        return giftRepository.findAllByUser(user);
    }

    public boolean canItBeDeleted(User user) {
        if (findUserGifts(user).size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Gift> hasInstitutionGifts(Institution institution) {
        return giftRepository.findAllByInstitution(institution);
    }

    public boolean canInstitutionBeDeleted(Institution institution) {
        return hasInstitutionGifts(institution).size() == 0;


    }

    public List<Gift> findByStatusAndUser(String status, User user) {
        return giftRepository.findAllByStatusAndUserOrderByPickUpTimeDesc(status, user);
    }

    public List<Gift> findByStatus(String status) {
        return giftRepository.findAllByStatus(status);
    }

    public int allBags() {
        List<Gift> giftList = giftRepository.findAll();
        int bags = 0;
        for (Gift g : giftList) {

            if (g.getStatus().equals("Sent")) {
                bags += g.getBags();
            }


        }
        return bags;

    }
    public List<Gift> sentGifts() {
        return giftRepository.findAllByStatus("Sent");
    }

    // JS form
    public Gift getFromForm(HttpServletRequest request) {
        // description
        String[] decsriptionTable = request.getParameterValues("products[]");
        StringBuilder decsriptionBuilder = new StringBuilder();
        for (String product : decsriptionTable) {
            decsriptionBuilder.append(product).append("; ");
        }
        String description = decsriptionBuilder.toString();

        int bags = Integer.parseInt(request.getParameter("bags"));

        Long idInstitution = Long.valueOf(request.getParameter("organization"));
        String street = request.getParameter("street");
        String homeNumber = request.getParameter("homeNumber");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("postcode");
        String phone = request.getParameter("phone");
        String courierDecsription = request.getParameter("more_info");

        String date = request.getParameter("data");
        String time = request.getParameter("time");

        String pickUpTimeFulString = date + "T" + time;
        LocalDateTime pickUpTime = LocalDateTime.parse(pickUpTimeFulString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));


        Gift gift = new Gift();
        gift.setDecsription(description);
        gift.setBags(bags);
        gift.setStreet(street);
        gift.setHomeNumber(homeNumber);
        gift.setCity(city);
        gift.setZipCode(zipCode);
        gift.setPhone(phone);
        gift.setCourierDecsription(courierDecsription);
        gift.setStatus("Courier");
        gift.setPickUpTime(pickUpTime);
        gift.setUser(userSession.getUserInSession());
        gift.setInstitution(institutionRepository.findOne(idInstitution));

        return gift;
    }

    // search
    public List<Gift> search(String search) {
        return giftRepository.findAllByDecsriptionContainingOrCityContainingOrHomeNumberContainingOrStreetContainingOrPhoneContainingOrZipCodeContaining(  search, search, search, search, search,search);
    }
}
