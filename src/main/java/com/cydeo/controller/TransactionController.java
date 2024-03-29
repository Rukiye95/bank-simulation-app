package com.cydeo.controller;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model){

        //what we need to provide to make transfer happen
            //we need to provide empty transaction object
           model.addAttribute("transactionDTO", new TransactionDTO());
            //we need all accounts to provide them as sender, receiver
           model.addAttribute("accounts",accountService.listAllActiveAccounts());
            //we need list of transactions(last 10) to fill table.(business logic is missing)
           model.addAttribute("lastTransactions",transactionService.last10Transactions());

        return "transaction/make-transfer";
    }

    //Write a post method, that takes transaction object from the method above
    //complete the make transfer and return the same page.
    @PostMapping("/transfer")
    public String postMakeTransfer(@Valid @ModelAttribute("transactionDTO") TransactionDTO transactionDTO, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){

            model.addAttribute("accounts",accountService.listAllAccount());
            return "transaction/make-transfer";
        }


        //I have UUID of accounts but I need to provide Account object.
        //I need to find Accounts based on the ID that I have and use as a parameter to makeTransfer method
        AccountDTO sender = accountService.retrieveById(transactionDTO.getSender().getId());
        AccountDTO receiver = accountService.retrieveById(transactionDTO.getReceiver().getId());

        transactionService.makeTransfer(sender,receiver, transactionDTO.getAmount(),new Date(), transactionDTO.getMessage());


        return "redirect:/make-transfer";
    }

    //write a method, that gets the account id from index.html and print on the console
    //(work on index.html and here)
    ///transaction/{id}
    // return transaction/transactions page.

    @GetMapping("/transaction/{id}")
    public String getTransactionList(@PathVariable("id") Long id, Model model){

        System.out.println(id);
        //get the list of transactions based on id and return as a model attribute
        //TASK - Complete the method (service and repository)
        ///findTransactionsListById
        model.addAttribute("transactions",transactionService.findTransactionListById(id));

        return "transaction/transactions";
    }


}
