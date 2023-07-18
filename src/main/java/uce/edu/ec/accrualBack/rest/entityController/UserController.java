package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.User;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/findAll", "/findAll/"})
    public List<User> listAll() {
        return userService.findAll();
    }

    @GetMapping("/findByUsername/{username}")
    public User listByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody User user) {
        userService.update(id, user);
    }

}
