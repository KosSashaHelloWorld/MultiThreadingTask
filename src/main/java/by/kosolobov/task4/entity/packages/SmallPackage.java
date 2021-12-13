package by.kosolobov.task4.entity.packages;

public class SmallPackage extends BasePackage {
    private static final int STANDARD_WEIGHT = 5;

    public SmallPackage() {
        super(STANDARD_WEIGHT);
    }

    @Override
    public String toString() {
        return String.format("SmallPackage{state:%s}", state);
    }
}
