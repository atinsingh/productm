package co.pragra.learning.newproductmanager.contoller;

import co.pragra.learning.newproductmanager.entity.User;
import co.pragra.learning.newproductmanager.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
       return this.userService.createUser(user);
    }

    @GetMapping("/api/user")
    public List<User> findAll(@RequestParam(value = "firstName", required = false) Optional<String> first,
                                @RequestParam(value = "lastName", required = false) Optional<String> last) {
        return this.userService.getAllByFilters(first,last);
    }

    @GetMapping("/api/user/{id}")
    public Optional<User> findById(@PathVariable ("id") Long id){
        return this.userService.findById(id);
    }

}
