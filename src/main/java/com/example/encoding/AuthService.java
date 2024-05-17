package com.example.encoding;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;

public class AuthService {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static void register(String username, String password) throws SQLException {
        String hashedPassword = encoder.encode(password);
        DatabaseHelper.createUser(username, hashedPassword);
    }

    public static boolean login(String username, String password) throws SQLException {
        User user = DatabaseHelper.findUserByUsername(username);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }
}
