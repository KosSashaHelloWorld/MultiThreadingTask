package by.kosolobov.task4.entity.packages;

public class BigPackage extends BasePackage {
    private static final int STANDARD_WEIGHT = 100;

    public BigPackage() {
        super(STANDARD_WEIGHT);
    }

    @Override
    public String toString() {
        return String.format("BigPackage{state:%s}", state);
    }
}
