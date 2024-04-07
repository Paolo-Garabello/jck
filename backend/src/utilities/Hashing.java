package utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

/**
 * Manages hashings for the main application.
 */
public class Hashing {
    private static MessageDigest md;
    /**
     * Uses MD5 algorithm to create a token made of 
     * the current timestamp and a random number.
     * @return a unique token.
     */
    public static String createToken() {
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(new Timestamp(System.currentTimeMillis()).toString().getBytes());
            md.update((md.digest().toString() + Math.random()).getBytes());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hash(String str) {
        try {
            md = MessageDigest.getInstance("SHA512");
            md.reset();
            md.update(str.getBytes());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
