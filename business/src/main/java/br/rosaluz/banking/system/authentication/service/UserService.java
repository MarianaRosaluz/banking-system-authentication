package br.rosaluz.banking.system.authentication.service;


import br.rosaluz.banking.system.authentication.model.User;

import java.util.Optional;

public interface UserService {

    public void create(User user) throws Exception;
    public User saveUser(User user);
    public Optional<User> findByLogin(String login);
    public Optional<User> findById(Long id);
    public boolean validateLoginAlredyExist(String login);

}
