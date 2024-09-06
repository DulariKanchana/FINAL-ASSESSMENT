package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController{

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "APP V 1.1.1";
    }

    @RequestMapping("/register")
    public String showRegisterPage(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }
    //mapping the post request
    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute User user){
        List<User> userList = userService.getAllUsers();
        System.out.println("userList.size()");
        System.out.println(userList.size());
        boolean fNameEquals= false, lNameEquals= false, emailEquals = false, duplicateFound = false;
        for(User user1 : userList){
            fNameEquals=  user1.getFirstName().equals(user.getFirstName());
            lNameEquals=  user1.getLastName().equals(user.getLastName());
            emailEquals=  user1.getEmail().equals(user.getEmail());

            if (fNameEquals && lNameEquals && emailEquals){
                duplicateFound = true;
            }
        }

        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        if (duplicateFound){
            System.out.println("user already exists");

        }
        else {
            System.out.println("User created : ID :"+userService.addUser(user));
            return "redirect:/dashboard";


        }

        return "redirect:/register";

    }


    @RequestMapping("/login")
    public String showLoginPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }
    @RequestMapping("/loginUser")
    public String loginUser(@Valid @ModelAttribute User user,BindingResult bindingResult){
        List<User> userList = userService.getAllUsers();
        boolean duplicateFound = false;
        for(User user1 : userList){
            boolean  passwordEquals=  user1.getPassword().equals(user.getPassword());

            boolean emailEquals=  user1.getEmail().equals(user.getEmail());

            if (passwordEquals && emailEquals){
                duplicateFound = true;
            }
        }
        if (duplicateFound){
            return "redirect:/dashboard";
        }
        else {
            System.out.println("User created : ID :"+userService.addUser(user));

        }
        if(bindingResult.hasErrors()){
//Return to the same page
            return "redirect:/login";
        }else {
//Work on the success logic
            return "/dashboard";
        }

//        return "redirect:/login";


    }

//    @Autowired
//    UserService userService;
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") User user, Model model) {
//        userService.registerUser(user);
//        model.addAttribute("successMessage", "User registered successfully!");
//        return "login";
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//        model.addAttribute("user", new User());
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String loginUser(@ModelAttribute User user, Model model) {
//        // Retrieve user by email
//        User existingUser = userService.findByEmail(user.getEmail());
//
//        if (existingUser != null) {
//            // User exists, login successful
//            return "redirect:/dashboard";
//        } else {
//            // User does not exist
//            model.addAttribute("errorMessage", "Invalid email.");
//            return "login";
//        }
//    }
//
//    @GetMapping("/entry")
//    public String transactionEntry(Model model) {
//        model.addAttribute("user", new User());
//        return "transaction_entry";
//    }
//
}