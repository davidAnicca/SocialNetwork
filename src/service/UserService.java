package service;

import domain.Friendship;
import domain.User;
import exceptions.RepoException;
import repo.FriendshipRepo;
import repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UserService {
    UserRepo repo;
    FriendshipRepo friendshipRepo;

    public UserService(UserRepo repo, FriendshipRepo friendshipRepo) {
        this.friendshipRepo = friendshipRepo;
        this.repo = repo;
    }

    public void addUser(String userName, String password) throws RepoException {
        repo.addUser(new User(userName, password));
    }

    public void removeUser(String userName) throws RepoException {
        repo.removeUser(new User(userName));
        removeFriendships(userName);
    }

    public Set<User> getUsers() {
        return repo.getUsers();
    }

    private boolean isUser(String userName) {
        return repo.find(new User(userName)) != null;
    }

    private boolean areUsers(String user1, String user2) {
        return isUser(user1) && isUser(user2);
    }

    public void addFriendship(String user1, String user2) throws RepoException {
        if (areUsers(user1, user2)) {
            friendshipRepo.addFriendship(new Friendship(user1, user2));
        }else throw new RepoException("prietenia se leagă doar între useri valizi");
    }

    public void removeFriendship(String user1, String user2) throws RepoException {
        if (areUsers(user1, user2)) {
            friendshipRepo.removeFriendship(new Friendship(user1, user2));
        }
    }

    public void removeFriendships(String user) throws RepoException {
        List<Friendship> friendshipsToBeDeleted = new ArrayList<>();
        for (Friendship friendship : friendshipRepo.getFriendships()) {
            if (Objects.equals(friendship.getUser2(), user) || Objects.equals(friendship.getUser1(), user)) {
                friendshipsToBeDeleted.add(friendship);
            }
        }
        for (Friendship friendship : friendshipsToBeDeleted) {
            friendshipRepo.removeFriendship(friendship);
        }
    }

    public Set<Friendship> getFriendships() {
        return friendshipRepo.getFriendships();
    }
}
