package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Transaction;

import com.example.demo.service.TransactionService;
@Controller

public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @RequestMapping("/transaction_entry")
    public String showTransactionForm(Model model) {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction) ;
        return "transaction_entry";
    }

    @PostMapping("/addTransaction")
    public String addTransaction(@Valid @ModelAttribute Transaction transaction,BindingResult bindingResult) {
        transactionService.saveTransaction(transaction);
        if (bindingResult.hasErrors()){
            //Return to the same page
            return "/transaction_entry";
        }else {
        //Work on the success logic
            return "redirect:/dashboard";
        }


    }

    @GetMapping("/transactions/history")
    public String showTransactionHistory(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transaction_history";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id){
        System.out.println("hello");
        transactionService.deleteTransaction(id);
        return "redirect:/transactions/history";
    }


    @GetMapping("/edit-page")
    public String editTransaction(Model model){
        Transaction transaction = new Transaction();
        model.addAttribute("transaction",transactionService.getAllTransactions());
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable Long id){
        model.addAttribute("transaction", transactionService.editTransaction(id));
//        transactionService.editTransaction(transaction);
        return "redirect:/edit";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "dashboard";
    }


}


//    @PostMapping("/addTransaction")
//    public String addTransaction(@ModelAttribute Transaction transaction) {
//        System.out.println(transaction.toString());
//        System.out.println(transaction.getAmount());
//        transactionService.saveTransaction(transaction);
//        return "redirect:/transaction_entry";
//    }
//
//    @GetMapping("/transactions/history")
//    public String showTransactionHistory(Model model) {
//        model.addAttribute("transactions", transactionService.getAllTransactions());
//        return "transaction_history";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteTransaction(@PathVariable int id){
//        System.out.println("hello");
//        transactionService.deleteTransaction(id);
//        return "redirect:/transactions/history";
//    }
//
//
//    @GetMapping("/edit-page")
//    public String editTransaction(Model model){
//            Transaction transaction = new Transaction();
//            model.addAttribute("transaction",transactionService.getAllTransactions());
//            return "edit";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editPage(Model model, @PathVariable int id){
//        model.addAttribute("transaction", transactionService.editTransaction(id));
////        transactionService.editTransaction(transaction);
//        return "redirect:/edit";
//    }
//
//    @GetMapping("/dashboard")
//    public String showDashboard(Model model) {
//        model.addAttribute("transactions", transactionService.getAllTransactions());
//        return "dashboard";
//    }
//
//
//}







