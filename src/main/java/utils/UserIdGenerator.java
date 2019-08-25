package utils;

import java.util.Objects;

public class UserIdGenerator {

    private static Long uniqueId;

    private UserIdGenerator() {
    }

    public static Long getId() {
        return (Objects.isNull(uniqueId)) ? uniqueId = 1L : ++uniqueId;
    }
}
