package by.kosolobov.task4.util;

public class VanIdGenerator {
    private static int id = 0;

    private VanIdGenerator() {}

    public static int generate() {
        return id++;
    }
}
