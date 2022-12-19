package model;

import utility.Encryptor;

public class User {
    int id;
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    int salt;

    public User(int id, String username, String password, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salt = Encryptor.generateSalt();
    }

    public int getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}