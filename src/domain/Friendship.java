package domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Friendship {
    /**
     * relație de prietenie dintre doi useri
     */
    String user1;
    String user2;

    public LocalDate getFriendsFrom() {
        return friendsFrom;
    }

    public void setFriendsFrom(LocalDate friendsFrom) {
        this.friendsFrom = friendsFrom;
    }

    LocalDate friendsFrom;

    public Friendship(String user1, String user2, LocalDate friendsFrom) {
        this.user1 = user1;
        this.user2 = user2;
        this.friendsFrom = friendsFrom;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(user1, that.user1) && Objects.equals(user2, that.user2) ||
                Objects.equals(user2, that.user1) && Objects.equals(user1, that.user2);
    }

    @Override
    public String toString() {
        return user1
                + " este prieten cu "
                + user2
                + " din "
                + friendsFrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }
}
