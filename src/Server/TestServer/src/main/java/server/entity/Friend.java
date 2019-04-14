package server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The type Friend.
 */
@Entity
@Table(name="friend")
public class Friend implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "userNameEntry")
    private String userNameEntry;
    @Column(name = "friend_name")
    private String friend_name;


    /**
     * Instantiates a new Friend.
     */
    public Friend() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets user name entry.
     *
     * @return the user name entry
     */
    public String getUser_name_entry() {
        return userNameEntry;
    }

    /**
     * Sets user name entry.
     *
     * @param userNameEntry the user name entry
     */
    public void setUser_name_entry(final String userNameEntry) {
        this.userNameEntry = userNameEntry;
    }

    /**
     * Gets friend name.
     *
     * @return the friend name
     */
    public String getFriend_name() {
        return friend_name;
    }

    /**
     * Sets friend name.
     *
     * @param friend_name the friend name
     */
    public void setFriend_name(final String friend_name) {
        this.friend_name = friend_name;
    }

    @Override
    public String toString() {
        return "Friend [id=" + id + ", userNameEntry="
                + userNameEntry + ", friend_name="
                + friend_name + "]";
    }

}





