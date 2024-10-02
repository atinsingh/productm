package co.pragra.learning.newproductmanager.service;

import co.pragra.learning.newproductmanager.entity.User;
import co.pragra.learning.newproductmanager.exception.InvalidUserDataException;
import co.pragra.learning.newproductmanager.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User createUser(User user) {
        // Write logic
        if(user == null) {
            throw new InvalidUserDataException("User can't be null");
        }
        if(user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new InvalidUserDataException("First can't be null or Empty");
        }
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        return repo.save(user);
    }

    public User updateUser(User user) {
        if(user == null) {
            throw new InvalidUserDataException("User can't be null");
        }
        Optional<User> userOptional = repo.findById(user.getId());

        userOptional.orElseThrow(()->new InvalidUserDataException("User Does't exits"));
        User dbUser = userOptional.get();
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setUpdateDate(new Date());
        return repo.save(dbUser);

    }


    public List<User> getAllByFilters(Optional<String> firstName,
                                      Optional<String> lastName ) {
        if(firstName.isPresent() && lastName.isPresent()) {
            return repo.findAllByFirstNameAndLastName(firstName.get(), lastName.get());
        }

        if(firstName.isPresent()) {
            return repo.findAllByFirstName(firstName.get());
        }
        if(lastName.isPresent()) {
            return repo.findAllByLastName(lastName.get());
        }
        return repo.findAll();
    }

    public Optional<User> findById(Long id) {
        return this.repo.findById(id);
    }
}
