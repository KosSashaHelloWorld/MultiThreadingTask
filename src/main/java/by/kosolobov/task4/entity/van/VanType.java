package by.kosolobov.task4.entity.van;

public enum VanType {
    EXPRESS(200, 800),
    SMALL(120, 200),
    MEDIUM(90, 500),
    BIG(60, 2000),
    DEFAULT(120, 500);

    final int speed;
    final int storageLimit;

    VanType(int speed, int storageLimit) {
        this.speed = speed;
        this.storageLimit = storageLimit;
    }
}
