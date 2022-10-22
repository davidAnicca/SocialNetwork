package userInterface;

import domain.User;
import exceptions.RepoException;
import service.UserService;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUiAdmin {
    private UserService service;

    public ConsoleUiAdmin(UserService service) {
        this.service = service;
    }

    public void run(){
        while (true){
            showMenu();
            if(!choose())break;
        }
    }

    private String readFromConsole(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private boolean choose() {
        System.out.print(">>");
        String opt = readFromConsole();
        switch (opt.strip()){
            case "":
                break;
            case "0":
                return false;
            case "1":
                addUserUi();
                break;
            case "2":
                removeUserUi();
                break;
            case "3":
                showUsersUi();
                break;
            default:
                System.out.println("optiune invalida");
        }
        return true;
    }

    private void showUsersUi() {
        for (User user: service.getUsers())
            System.out.println(user);
    }

    private void removeUserUi() {
        System.out.println("user name>>");
        String userName = readFromConsole();
        try {
            service.removeUser(userName);
        }catch (RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void addUserUi() {
        System.out.print("user name>>");
        String userName = readFromConsole();
        System.out.print("parola>>");
        String password = readFromConsole();
        try {
            service.addUser(userName.strip(), password.strip());
        }catch (RepoException e){
            System.out.println(e.getMessage());
        }
    }

    private void showMenu() {
        System.out.println("0.Exit");
        System.out.println("1.Adauga utilizator");
        System.out.println("2.Sterge utilizator");
        System.out.println("3.Vezi toti utilizatorii");
    }
}
