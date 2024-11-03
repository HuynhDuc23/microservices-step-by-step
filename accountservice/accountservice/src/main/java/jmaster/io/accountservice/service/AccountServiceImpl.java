package jmaster.io.accountservice.service;

import jmaster.io.accountservice.entity.Account;
import jmaster.io.accountservice.model.AccountDTO;
import jmaster.io.accountservice.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository ;
    @Autowired
    private ModelMapper modelMapper ;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setName(accountDTO.getName());
        Set<String> roleAccount = new HashSet<>();
        for(String role : accountDTO.getRoles()){
            roleAccount.add(role);
        }
        account.setRoles(roleAccount);
        accountRepository.save(account);
    }

    @Override
    public void update(AccountDTO accountDTO) {
        Account account = accountRepository.getById(accountDTO.getId());
        if (account != null) {
            modelMapper.typeMap(AccountDTO.class, Account.class)
                    .addMappings(mapper -> mapper.skip(Account::setPassword)).map(accountDTO, account);

            accountRepository.save(account);
        }
    }

    @Override
    public void updatePassword(AccountDTO accountDTO) {
        Account account = accountRepository.getById(accountDTO.getId());
        if (account != null) {
            //account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
            accountRepository.save(account);
        }
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.getById(id);
        if (account != null) {
            accountRepository.delete(account);
        }
    }

    @Override
    public List<AccountDTO> getAll() {
        List<AccountDTO> accountDTOs = new ArrayList<>();

        accountRepository.findAll().forEach((account) -> {
            accountDTOs.add(modelMapper.map(account, AccountDTO.class));
        });

        return accountDTOs;
    }

    @Override
    public AccountDTO getOne(Long id) {
        Account account = accountRepository.getById(id);

        if (account != null) {
            return modelMapper.map(account, AccountDTO.class);
        }

        return null;
    }
}
