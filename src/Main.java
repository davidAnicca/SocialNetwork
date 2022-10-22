import domain.User;
import exceptions.RepoException;
import repo.UserRepo;
import service.UserService;

public class Main {
    public static void main(String[] args) throws RepoException {
        testRepo();
        testSrv();
    }

    private static void testRepo() {
        UserRepo repo = new UserRepo("users.csv");
        try {
            repo.addUser(new User("bla", "bla"));
        } catch (RepoException ignored) {
            System.out.println("repo add error");
        }
        try {
            repo.addUser(new User("bla", "bla"));
            System.out.println("repo add error");
        } catch (RepoException ignored) {

        }
        try {
            repo.removeUser(new User("bla", "bla"));
        } catch (RepoException ignored) {
            System.out.println("repo remove error");
        }
        try {
            repo.removeUser(new User("bla", "bla"));
            System.out.println("repo remove error");
        } catch (RepoException ignored) {

        }
    }

    private static void testSrv() {
        UserRepo repo = new UserRepo("users.csv");
        UserService service = new UserService(repo);
        try {
            service.addUser("bla", "bla");
        } catch (RepoException ignored) {
            System.out.println("srv add error");
        }
        try {
            service.addUser("bla", "bla");
            System.out.println("srv add error");
        } catch (RepoException ignored) {

        }
        try {
            service.removeUser("bla");
        } catch (RepoException ignored) {
            System.out.println("srv remove error");
        }
        try {
            service.removeUser("bla");
            System.out.println("srv remove error");
        } catch (RepoException ignored) {

        }
    }
}