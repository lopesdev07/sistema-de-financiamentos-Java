package main.java.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String plainToHash(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
