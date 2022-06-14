package br.rosaluz.banking.system.authentication.service;


import br.rosaluz.banking.system.authentication.exception.BankingSystemAuthenticationValueAlredyExistsException;
import br.rosaluz.banking.system.authentication.feign.AccountFeignClient;
import br.rosaluz.banking.system.authentication.model.User;
import br.rosaluz.banking.system.authentication.producer.UserProducer;
import br.rosaluz.banking.system.authentication.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserProducer userProducer;

    private UserRepository userRepository;
@Autowired
    private AccountFeignClient accountFeignClient;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void create(User user) throws Exception {

        if (!validateLoginAlredyExist(user.getLogin()))
            throw new BankingSystemAuthenticationValueAlredyExistsException("Field Value Alredy Exists", "login");

        if (!validateDocumentAlredyExist(user.getDocument()))
            throw new BankingSystemAuthenticationValueAlredyExistsException("Field Value Alredy Exists", "document");

        User userSaved = saveUser(user);
        if (userSaved != null) {
           var account =  accountFeignClient.createAccount(userSaved.getId().toString());
           user.setAccountNumber(account.getAccountNumber());
           saveUser(user);
           userProducer.send(user);
        }
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void process(String menssage) {
        updateWithAccount((long) 1, menssage);
    }

    @Override
    public Optional<User> findByLogin(String login) {

        return userRepository.findByLogin(login);

    }

    @Override
    public Optional<User> findByDocument(String document) {

        return userRepository.findByDocument(document);

    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean validateLoginAlredyExist(String login) {

        if (findByLogin(login).isPresent())
            return false;
        else
            return true;
    }

    private boolean validateDocumentAlredyExist(String document) {

        if (findByDocument(document).isPresent())
            return false;
        else
            return true;
    }

    @Override
    public void updateWithAccount(Long id, String accountNumber) {
        var userOptional = findById(id);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            user.setAccountNumber(accountNumber);
            saveUser(user);
        }
    }


}
