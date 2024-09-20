package com.iiitd.registration;

import static com.iiitd.registration.Main.userMap;

public class User {
    protected String email;
    protected String password;

    public User () {
        this.email = "";
        this.password = "";
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public String getEmail() {
        return email;
    }

    static User findUser(String email) {
        return userMap.get(email);
    }

}
