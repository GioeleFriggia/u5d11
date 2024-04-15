package gioelefriggia.u5d11.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import gioelefriggia.u5d11.exceptions.BadRequestException;
import gioelefriggia.u5d11.payloads.NewUserDTO;
import gioelefriggia.u5d11.payloads.NewUserRespDTO;
import gioelefriggia.u5d11.payloads.UserLoginDTO;
import gioelefriggia.u5d11.payloads.UserLoginResponseDTO;
import gioelefriggia.u5d11.services.AuthService;
import gioelefriggia.u5d11.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){

        if(validation.hasErrors()) {  Bad Request
            throw new BadRequestException(validation.getAllErrors());
        }
        // Altrimenti se non ci sono stati errori posso salvare tranquillamente lo user
        return new NewUserRespDTO(this.usersService.save(body).getId());
    }

}
