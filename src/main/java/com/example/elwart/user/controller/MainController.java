package com.example.elwart.user.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private UserService userService;
    private DelegationService delegationService;

    @Autowired
    public MainController(UserService userService, DelegationService delegationService) {
        this.userService = userService;
        this.delegationService = delegationService;
    }


    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public void registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }

    @PutMapping("/user/change_password/{id}")
    public void changePassword(@PathVariable Long id, @RequestParam String password) {
        userService.changePassword(id, password);
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUserById(@PathVariable Long id) {
       return userService.deleteUserById(id);
    }

    @PostMapping("/delegation")
    public void addDelegation(@RequestBody DelegationDto delegationDto, @RequestParam Long userId) throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
       delegationService.addDelegation(delegationDto,userId);
    }
    @DeleteMapping("/delegation/removeDelegation")
    public boolean removeDelegation(@RequestParam Long delegationId, @RequestParam Long userId){
        return  delegationService.removeDelegation(delegationId,userId);
    }
    @PutMapping("/delegation/{id}")
    public void changeDelegation(@RequestBody DelegationDto delegationDto, @PathVariable Long id) throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        delegationService.changeDelegation(delegationDto,id);
    }
    @GetMapping("/delegation")
    public List<Delegation> getAllDelegations(){
        return delegationService.getAllDelegations();
    }
    @GetMapping("/user/getByRole")
    public List<User> getAllByRoleName(@RequestParam String roleName){
        return  userService.getAllByRole(roleName);
    }
    @GetMapping("/delegation/sortByDate")
    public List <Delegation> getAllByDate(){
        return delegationService.getAllByDateStartDesc();
    }
    @GetMapping("/delegation/sortByUserDate")
    public List<Delegation> getAllByUserAndDate(@RequestParam  Long userId){
        return delegationService.getAllByUserAndDateStartDesc(userId);
    }
}
