package org.example.appexamen.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName="user_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="user_sequence"
    )
    private Long id;

    @Column(name="username", nullable = false)
    private String username;
    @Column(name="password", nullable = false, length=255)
    private String password;
    @Column(name = "isactive")
    private Boolean isactive;
    @Column(name = "isbuyer")
    private Boolean isbuyer;

    public User() {

    }

    public User(String username, Long id, String password, Boolean isactive, Boolean isbuyer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isactive = isactive;
        this.isbuyer = isbuyer;
    }

    public User(String username, String password, Boolean isactive, Boolean isbuyer) {
        this.username = username;
        this.password = password;
        this.isactive = isactive;
        this.isbuyer = isbuyer;
    }

    public User(String username, String password) {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isactive;
    }

    public void setActive(Boolean active) {
        isactive = active;
    }

    public boolean isBuyer() {
        return isbuyer;
    }

    public void setBuyer(Boolean buyer) {
        isbuyer = buyer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isactive +
                ", isBuyer=" + isbuyer +
                '}';
    }
}
