package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

//    long countByLogin(String login);

    long countByEmail(String email);

    User findUsersByEmail(String email);

}
