import domain.Friendship;
import exceptions.RepoException;
import repo.db.FriendshipRepoDb;
import repo.db.UserRepoDb;
import service.UserService;
import userInterface.ConsoleUiAdmin;

public class Main {
    public static void main(String[] args) throws RepoException {
        //testRepo();
        ///testSrv();
        ConsoleUiAdmin consoleUiAdmin = new ConsoleUiAdmin(
                new UserService(
                        new UserRepoDb("jdbc:postgresql://localhost:5432/SocialNetworkDB",
                                "postgres",
                                "adenozintrifosfat"),
                        new FriendshipRepoDb("jdbc:postgresql://localhost:5432/SocialNetworkDB",
                                "postgres",
                                "adenozintrifosfat")
                )
        );
        consoleUiAdmin.run();
    }
}