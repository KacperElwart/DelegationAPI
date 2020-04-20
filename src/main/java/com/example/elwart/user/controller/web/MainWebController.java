package com.example.elwart.user.controller.web;

import com.example.elwart.user.dto.DelegationDto;
import com.example.elwart.user.dto.UserDto;
import com.example.elwart.user.exception.BadAutoCapacityException;
import com.example.elwart.user.exception.NotKmException;
import com.example.elwart.user.exception.NotTicketPriceException;
import com.example.elwart.user.model.Delegation;
import com.example.elwart.user.model.User;
import com.example.elwart.user.service.DelegationService;
import com.example.elwart.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainWebController {

    private UserService userService;
    private DelegationService delegationService;

    @Autowired
    public MainWebController(UserService userService, DelegationService delegationService) {
        this.userService = userService;
        this.delegationService = delegationService;
    }

    @GetMapping("/")
    public String getIndex(Authentication auth, Model model){
        User user = userService.getUserByEmail(auth.getName());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessge);
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user",user);
        return "registration";
    }
    @PostMapping("/register")
    public String saveRegister(UserDto userDto){
        userService.registerUser(userDto);
        return "login";
    }
    @GetMapping("/loginSuccess")
    public String successLogin( Authentication auth, Model model) {
        User user = userService.getUserByEmail(auth.getName());
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/delegation/add")
    public String getDelegationForm (Model model){
        DelegationDto delegation = new DelegationDto();
        model.addAttribute("delegation",delegation);
        return "add-delegation";
    }
    @PostMapping("/delegation/save")
    public String saveDelegation (DelegationDto delegationDto, Authentication auth) throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        delegationDto.setTimes();
        User user = userService.getUserByEmail(auth.getName());
        delegationService.addDelegation(delegationDto,user.getId());
        return "redirect:/";
    }
    @PostMapping("/delegation/deleteDel")
    public String delete(@RequestParam("delegationId") Long theId,Authentication auth) {
        User user = userService.getUserByEmail(auth.getName());
        delegationService.removeDelegation(theId,user.getId());
        return "redirect:/";

    }
    @GetMapping("/403")
    public String getError403(){
        return "403";
    }
}
