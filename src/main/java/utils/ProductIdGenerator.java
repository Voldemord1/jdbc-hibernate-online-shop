package utils;

import java.util.Objects;

public class ProductIdGenerator {

    private static Long uniqueId;

    private ProductIdGenerator() {
    }

    public static Long getId() {
        return (Objects.isNull(uniqueId)) ? uniqueId = 1L : ++uniqueId;
    }

}
