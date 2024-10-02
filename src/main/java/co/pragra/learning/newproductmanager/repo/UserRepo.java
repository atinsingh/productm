package co.pragra.learning.newproductmanager.repo;

import co.pragra.learning.newproductmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByFirstName(String firstName);
    List<User> findAllByLastName(String lastName);
    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);


}
