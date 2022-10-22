package service;

public class Validator {
    public static boolean userNameValidator(String userName){
        return userName.matches("[a-zA-Z0-9]");
    }
}
