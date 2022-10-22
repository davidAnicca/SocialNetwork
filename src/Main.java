import Domain.User;
import Exceptions.RepoException;
import Repo.UserRepo;

public class Main {
    public static void main(String[] args) throws RepoException {
        testRepo();
    }

    private static void testRepo(){
        UserRepo repo = new UserRepo("users.csv");
        try {
            repo.addUser(new User("bla", "bla"));
        }catch (RepoException ignored){
            System.out.println("repo add error");
        }
        try {
            repo.addUser(new User("bla", "bla"));
            System.out.println("repo add error");
        }catch (RepoException ignored){

        }
        try {
            repo.removeUser(new User("bla", "bla"));
        }catch (RepoException ignored){
            System.out.println("repo remove error");
        }
        try {
            repo.removeUser(new User("bla", "bla"));
            System.out.println("repo remove error");
        }catch (RepoException ignored){

        }
    }
}