package by.kosolobov.task4.entity.packages;

public enum BoxType {
    EXTRA_SMALL(1),
    SMALL(5),
    MEDIUM(25),
    BIG(100),
    GIANT(250),
    DEFAULT(25);

    final int weight;

    BoxType(int weight) {
        this.weight = weight;
    }
}
