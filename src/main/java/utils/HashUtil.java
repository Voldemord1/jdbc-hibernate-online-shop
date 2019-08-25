package utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashUtil {

    private static final Logger logger = Logger.getLogger(HashUtil.class);
    private static final int SALT_LENGTH = 10;

    public static String getHash(String password, String salt) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = (password + salt).getBytes();
            byte[] passwordHashBytes = sha256.digest(passwordBytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte hash : passwordHashBytes) {
                stringBuilder.append(Integer.toString((hash & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException " + e);
        }
        return password;
    }

    public static String getSalt() {
        int letterA = 97;
        int letterZ = 122;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(SALT_LENGTH);
        for (int i = 0; i < SALT_LENGTH; i++) {
            int character = letterA + (int) (random.nextFloat() * (letterZ - letterA + 1));
            stringBuilder.append((char) character);
        }
        return stringBuilder.toString();
    }
}
