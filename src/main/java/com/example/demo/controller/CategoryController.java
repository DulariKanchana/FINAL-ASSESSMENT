package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;





@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/category_management")
    public String categoryManagement(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category_management";
    }


}
