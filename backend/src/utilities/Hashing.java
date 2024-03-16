package utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class Hashing {
    
    public static String createToken() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(new Timestamp(System.currentTimeMillis()).toString().getBytes());
            md.update((md.digest().toString() + Math.random()).getBytes());
            BigInteger bigInt = new BigInteger(1, md.digest());
            return bigInt.toString(16);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
