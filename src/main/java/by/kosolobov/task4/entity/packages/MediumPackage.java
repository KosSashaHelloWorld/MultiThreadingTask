package by.kosolobov.task4.entity.packages;

public class MediumPackage extends BasePackage {
    private static final int STANDARD_WEIGHT = 25;

    public MediumPackage() {
        super(STANDARD_WEIGHT);
    }

    @Override
    public String toString() {
        return String.format("MediumPackage{state:%s}", state);
    }
}
