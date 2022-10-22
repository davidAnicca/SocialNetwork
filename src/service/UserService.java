package service;

import domain.User;
import exceptions.RepoException;
import repo.UserRepo;

public class UserService {
    UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public void addUser(String userName, String password) throws RepoException {
        repo.addUser(new User(userName, password));
    }

    public void removeUser(String userName) throws RepoException {
        repo.removeUser(new User(userName));
    }
}
