package repo;

import domain.User;
import exceptions.RepoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class UserRepo {
    private final Set<User> users = new HashSet<>();
    private String filePath;

    public Set<User> getUsers() {
        return users;
    }

    public UserRepo(String filePath) {
        this.filePath = filePath;
        getUsersFromFile();
    }

    private void getUsersFromFile() {
        users.clear();
        try {
            File file = new File(filePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split(",");
                User newUser = new User(split[0], split[1]);
                users.add(newUser);
                ///System.out.println(newUser);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a putut găsi fișierul de import");
            e.printStackTrace();
        }
    }

    public User find(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1, user))
                return user1;
        }
        return null;
    }

    private void saveUsersInFile() {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (User user : users) {
                myWriter.write(user.getUserName()
                        + ","
                        + user.getPassword()
                        + ",\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Nu s-au putut salva datele în fișier");
            e.printStackTrace();
        }
    }

    public void addUser(User user) throws RepoException {
        if (!users.add(user)) {
            throw new RepoException("Userul există deja\n");
        }
        saveUsersInFile();
    }

    public void removeUser(User user) throws RepoException {
        if (!users.remove(user)) {
            throw new RepoException("Userul nu există\n");
        }
        saveUsersInFile();
    }

}
