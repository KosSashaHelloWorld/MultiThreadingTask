package by.kosolobov.task4.entity.packages;

import by.kosolobov.task4.entity.state.BoxState;
import by.kosolobov.task4.entity.state.impl.UnorderedState;

import static by.kosolobov.task4.entity.packages.BoxType.DEFAULT;

public class Box {
    private final int weight;
    private BoxState state = new UnorderedState();

    public Box() {
        weight = DEFAULT.getWeight();
    }

    public Box(int weight) {
        this.weight = weight;
    }

    public Box(BoxType type) {
        weight = type.getWeight();
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

    public void setState(BoxState newState) {
        state = newState;
    }

    public int getWeight() {
        return weight;
    }

    public BoxState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Box that = (Box) o;

        return weight == that.weight;
    }

    @Override
    public int hashCode() {
        return weight;
    }
}
