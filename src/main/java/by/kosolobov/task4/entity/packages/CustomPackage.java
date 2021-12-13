package by.kosolobov.task4.entity.packages;

public class CustomPackage extends BasePackage {

    public CustomPackage(int weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return String.format("CustomPackage{state:%s, weight:%d}", state, weight);
    }
}
