package Control;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashControl {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String hashPassword(int password) {
        return hashPassword(String.valueOf(password));
    }

    public static boolean checkPassword(String password, String hash) {
        return hash.equals(hashPassword(password));
    }

    public static boolean checkPassword(int password, String hash) {
        return checkPassword(String.valueOf(password), hash);
    }
}
