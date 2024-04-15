package gioelefriggia.u5d11.controllers;

import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import gioelefriggia.u5d11.entities.Dipendente;
import gioelefriggia.u5d11.exceptions.BadRequestException;
import gioelefriggia.u5d11.payloads.NewUserDTO;
import gioelefriggia.u5d11.payloads.NewUserRespDTO;
import gioelefriggia.u5d11.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return this.usersService.getUsers(page, size, sortBy);
    }


    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId){
        return this.usersService.findById(userId);
    }

    @PutMapping("/{userId}")
    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody User body){
        return this.usersService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId){
        this.usersService.findByIdAndDelete(userId);
    }

}
