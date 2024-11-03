package jmaster.io.accountservice.service;

import jmaster.io.accountservice.model.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {
    public void add (AccountDTO accountDTO);
    public void update(AccountDTO accountDTO);
    public void updatePassword(AccountDTO accountDTO);
    public void delete(Long id);
    List<AccountDTO> getAll();
    AccountDTO getOne(Long id);
}
