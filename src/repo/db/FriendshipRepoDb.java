package repo.db;

import domain.Friendship;
import domain.User;
import exceptions.RepoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FriendshipRepoDb {
    /**
     * repo pentru relațiile de prietenie
     */
    private final Set<Friendship> friendships = new HashSet<>();

    private String url;
    private String userName;
    private String password;

    /**
     * constructor
     *
     * @param url      conectare la bd
     * @param userName user name pt baza de dat
     * @param password parola pt baza de date
     */
    public FriendshipRepoDb(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        loadData();
    }


    /**
     * preia datele din baza de date
     */
    private void loadData() {
        friendships.clear();
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String user1 = resultSet.getString("user1");
                String user2 = resultSet.getString("user2");
                LocalDate from = resultSet.getDate("ffrom").toLocalDate();
                Friendship friendship = new Friendship(user1, user2, from);
                friendships.add(friendship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * obține mulțimea tuturor relațiilor de prietenie
     *
     * @return o mulțime care conține toate relațiile de prietenie din aplicație
     */
    public Set<Friendship> getFriendships() {
        return friendships;
    }


    /**
     * adaugă o relație de prietenie
     *
     * @param friendship relația de prietenie care trebuie adăugată
     * @throws RepoException dacă relația există deja
     */
    public void addFriendship(Friendship friendship) throws RepoException {
        for(Friendship friendship1: friendships){
            if(friendship1.equals(friendship)) {
                throw new RepoException("prietenia există deja");
            }
        }
        if (!friendships.add(friendship)) {
            throw new RepoException("prietenia exista deja");
        }
        saveFriendship(friendship);
    }

    private void saveFriendship(Friendship friendship) {
        String sql = "insert into friendships (user1, user2, ffrom) values (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, friendship.getUser1());
            ps.setString(2, friendship.getUser2());
            ps.setDate(3, Date.valueOf(friendship.getFriendsFrom()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * șterge o relație de prietenie
     *
     * @param friendship relația care ar trebui ștearsă
     * @throws RepoException dacă relația nu există
     */
    public void removeFriendship(Friendship friendship) throws RepoException {
        for(Friendship friendship1 : friendships) {
            if (friendship1.equals(friendship)) {
                friendships.remove(friendship);
                removeFriendshipFromDb(friendship);
                return;
            }
        }
        throw new RepoException("prietenia nu există");
    }

    private void removeFriendshipFromDb(Friendship friendship) {
        String sql = "delete from friendships where user1=? and user2=?";
        String sql2 = "delete from friendships where user1=? and user2=?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, friendship.getUser1());
            ps.setString(2, friendship.getUser2());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(sql2)) {

            ps.setString(2, friendship.getUser1());
            ps.setString(1, friendship.getUser2());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
