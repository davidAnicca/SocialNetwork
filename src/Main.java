import domain.User;
import exceptions.RepoException;
import exceptions.ServiceException;
import repo.FriendshipRepo;
import repo.UserRepo;
import service.UserService;
import userInterface.ConsoleUiAdmin;

public class Main {
    public static void main(String[] args) throws RepoException {
        //testRepo();
        ///testSrv();
        ConsoleUiAdmin consoleUiAdmin = new ConsoleUiAdmin(
                new UserService(
                        new UserRepo("users.csv"),
                        new FriendshipRepo("friendships.csv")
                )
        );
        consoleUiAdmin.run();
    }

    private static void testRepo() {
        UserRepo repo = new UserRepo("users.csv");
        try {
            repo.addUser(new User("bla", "bla@bla.com", "bla", null));
        } catch (RepoException ignored) {
            System.out.println("repo add error");
        }
        try {
            repo.addUser(new User("bla", "bla@bla.com", "bla" , null));
            System.out.println("repo add error");
        } catch (RepoException ignored) {

        }
        try {
            repo.removeUser(new User("bla", "bla@bla.com", "bla", null));
        } catch (RepoException ignored) {
            System.out.println("repo remove error");
        }
        try {
            repo.removeUser(new User("bla", "bla@bla.com", "bla", null));
            System.out.println("repo remove error");
        } catch (RepoException ignored) {

        }
    }

    private static void testSrv() {
        UserRepo repo = new UserRepo("users.csv");
        UserService service = new UserService(repo, null);
        try {
            service.addUser("bla", "bla@bla.com", "bla", "2002-01-01");
        } catch (RepoException ignored) {
            System.out.println("srv add error");
        }catch (ServiceException ignored){
            System.out.println("srv date val err");
        }
        try {
            service.addUser("bla", "bla@bla.com", "bla", "2001-01-01");
            System.out.println("srv add error");
        } catch (RepoException | ServiceException ignored) {

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