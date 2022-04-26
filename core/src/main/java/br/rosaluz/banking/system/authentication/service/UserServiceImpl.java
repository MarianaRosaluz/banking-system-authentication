package br.rosaluz.banking.system.authentication.service;

import br.rosaluz.banking.system.authentication.repository.UserRepository;
import br.rosaluz.banking.system.authentication.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
      this.userRepository = userRepository;
    }


    @Override
    public void create(User user) throws Exception {

          if(validateLoginAlredyExist(user.getLogin()))
              throw new Exception("");

          saveUser(user);
    }

    @Override
    public User saveUser(User user)
    {
         return userRepository.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login){

        return  userRepository.findByLogin(login);

    }

    @Override
    public Optional<User> findById(Long id){
        return  userRepository.findById(id);
    }

    @Override
    public  boolean validateLoginAlredyExist(String login){

        if(findByLogin(login).isPresent())
            return false;
        else
            return true;
    }


}