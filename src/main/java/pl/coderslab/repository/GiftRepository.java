package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Gift;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {

    List<Gift> findAllByUser(User user);

    List<Gift> findAllByInstitution(Institution institution);

    List<Gift> findAllByStatusAndUserOrderByPickUpTimeDesc(String status, User user);

    List<Gift> findAllByStatus(String status);



}
