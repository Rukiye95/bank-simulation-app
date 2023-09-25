package com.cydeo;

import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);

        //get account and transaction service beans
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);
//
        //create 2 account sender and receiver.
//        Account sender = accountService.createNewAccount(BigDecimal.valueOf(70),new Date(), AccountType.CHECKING,1L);
//
//        Account receiver = accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.CHECKING,2L);
//        Account receiver2 = accountService.createNewAccount(BigDecimal.valueOf(432),new Date(), AccountType.SAVING,123L);
//        Account receiver3 = accountService.createNewAccount(BigDecimal.valueOf(6453),new Date(), AccountType.CHECKING,125L);
////        accountService.listAllAccount().forEach(System.out::println);
//
//        transactionService.makeTransfer(sender,receiver,new BigDecimal(40),new Date(),"Transaction 1");
//
//        System.out.println(transactionService.findAllTransaction().get(0));
//
//        accountService.listAllAccount().forEach(System.out::println);


    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
