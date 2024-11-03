package jmaster.io.accountservice.controller;

import jmaster.io.accountservice.client.NotificationService;
import jmaster.io.accountservice.client.StatisticService;
import jmaster.io.accountservice.model.AccountDTO;
import jmaster.io.accountservice.model.MessageDTO;
import jmaster.io.accountservice.model.StatisticDTO;
import jmaster.io.accountservice.service.AccountService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

public class AccountController {
    @Autowired
    private  AccountService accountService;
    @Autowired
    private  StatisticService statisticService ;
    @Autowired
    private NotificationService notificationService ;
    // add new
    @PostMapping("/account")
    public ResponseEntity<String> addAccount(@RequestBody AccountDTO accountDTO) {
        accountService.add(accountDTO);
        this.statisticService.add(new StatisticDTO(accountDTO.getUsername() + "created" , new Date()));
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("ductrantad23@gmail.com") ;
        messageDTO.setTo(accountDTO.getUsername());
        messageDTO.setSubject(accountDTO.getUsername());
        messageDTO.setContent("ok");
        messageDTO.setToName("ok") ;
        this.notificationService.sendNotification(messageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDTO.getUsername() + "created");
    }

    // get all
    @GetMapping("/accounts")
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> get(@PathVariable(name = "id") Long id) {
        return Optional.of(new ResponseEntity<AccountDTO>(accountService.getOne(id), HttpStatus.OK))
                .orElse(new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        accountService.delete(id);
    }

    @PutMapping("/account")
    public void update(@RequestBody AccountDTO accountDTO) {
        accountService.update(accountDTO);
    }
}
