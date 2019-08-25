package utils;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HashUtilTest {

    private static final String PASSWORD_FOR_TEST_1 = "12345";
    private static final String SALT_FOR_TEST_1 = "qwerty";
    private static final String PASSWORD_FOR_TEST_2 = "";
    private static final String SALT_FOR_TEST_2 = "qwerty";
    private static final String PASSWORD_FOR_TEST_3 = "123";
    private static final String SALT_FOR_TEST_3 = "";

    @Test
    public void getHash() {
        String actualHash = HashUtil.getHash(PASSWORD_FOR_TEST_1, SALT_FOR_TEST_1);
        String expectedHash = "ba467682f4b8c0a1f4f74137cf39598569e13692eea3146b9107ed61888bb499";
        assertEquals(actualHash, expectedHash);
    }

    @Test
    public void getHashWithEmptyPassword() {
        String actualHash = HashUtil.getHash(PASSWORD_FOR_TEST_2, SALT_FOR_TEST_2);
        String expectedHash = "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5";
        assertEquals(actualHash, expectedHash);
    }

    @Test
    public void getHashWithEmptySalt() {
        String actualHash = HashUtil.getHash(PASSWORD_FOR_TEST_3, SALT_FOR_TEST_3);
        String expectedHash = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";
        assertEquals(actualHash, expectedHash);
    }

    @Test
    public void getHashWithEmptyPasswordAndSalt() {
        String actualHash = HashUtil.getHash("", "");
        String expectedHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
        assertEquals(actualHash, expectedHash);
    }
}
