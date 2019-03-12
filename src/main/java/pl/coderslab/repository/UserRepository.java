package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

//    long countByLogin(String login);

    long countByEmail(String email);

    User findUsersByEmail(String email);

    List<User> findUsersBySuperUser(boolean check);

    long countBySuperUser(boolean check);

    List<User> findUserByLastNameContainingOrFirstNameContainingOrEmailContaining(String lastName, String firstName,String email);

}
