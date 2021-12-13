package by.kosolobov.task4.entity.packages;

import by.kosolobov.task4.entity.state.BasePackageState;
import by.kosolobov.task4.entity.state.impl.UnorderedState;

public abstract class BasePackage {
    protected final int weight;
    protected BasePackageState state = new UnorderedState();

    protected BasePackage(int weight) {
        this.weight = weight;
    }

    public void printStatus() {
        state.printStatus();
    }

    public void nextState() {
        state.next(this);
    }

    public void prevState() {
        state.prev(this);
    }

    public void setState(BasePackageState newState) {
        state = newState;
    }

    public int getWeight() {
        return weight;
    }

    public BasePackageState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasePackage that = (BasePackage) o;

        return weight == that.weight;
    }

    @Override
    public int hashCode() {
        return weight;
    }
}
