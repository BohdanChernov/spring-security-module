package com.springsecurity.module.controllers;

import com.springsecurity.module.dao.UserDAO;
import com.springsecurity.module.models.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers/")
public class CustomerController {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerController(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        System.out.println();
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getPage() {
        return "api/customers/";
    }

    @PostMapping("register")
    @PreAuthorize("hasAuthority('customer:write')")
    public void registerCustomer(@RequestBody UserImpl userImpl) {
        userImpl.setPassword(passwordEncoder.encode(userImpl.getPassword()));
        userDAO.save(userImpl);
    }

    @GetMapping(path = "read/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('customer:read')")
    public UserImpl readCustomer(@PathVariable("id") Long id) {
        return userDAO.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s is not found", id)));
    }

    @PutMapping(path = "update/{id}")
    @PreAuthorize("hasAuthority('customer:write')")
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody UserImpl userImpl) {
        UserImpl user = userDAO.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s is not found", id)));
        user.setName(userImpl.getName());
        user.setLastName(userImpl.getLastName());
        user.setPhoneNumber(userImpl.getPhoneNumber());
        user.setEmail(userImpl.getEmail());
        user.setPassword(passwordEncoder.encode(userImpl.getPassword()));
        userDAO.save(user);
    }

    @DeleteMapping(path = "delete/{id}")
    @PreAuthorize("hasAuthority('customer:write')")
    public void deleteCustomer(@PathVariable("id") Long id) {
        userDAO.deleteById(id);
    }
}
