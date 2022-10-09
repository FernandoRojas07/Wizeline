package com.wizeline.DTO;

import java.util.Map;

public class UserDTO {

    private String user;

    private String password;

    private User(UserBuilder userBuilder){
        this.user = userBuilder.user;
        this.pass = userBuilder.pass;
        this.tipo = userBuilder.tipo;
        this.administrador = userBuilder.administrador;
        this.edad = userBuilder.edad;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO getParameters(Map<String, String> userParam){
        UserDTO user = new UserDTO();
        user.setUser(userParam.get("user"));
        user.setPassword(userParam.get("password"));
        return user;
    }

    public static final class UserBuilder{
        private String user;
        private String pass;

        public UserBuilder() {
        }

        public UserBuilder user(String user) {
            this.user = user;
            return this;
        }
        public UserBuilder pass(String pass) {
            this.pass = pass;
            return this;
        }

        public User build() {
            User user =  new User(this);
            return user;
        }
    }
}
