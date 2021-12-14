package by.kosolobov.task4.util;

public class BoxIdGenerator {
    private static int id = 0;

    private BoxIdGenerator() {}

    public static int generate() {
        return id++;
    }
}
