package by.kosolobov.task4.entity.packages.impl;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.state.BasePackageState;
import by.kosolobov.task4.entity.state.impl.UnorderedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigPackage implements BasePackage {
    private static final int WEIGHT = 100;
    private BasePackageState state = new UnorderedState();

    @Override
    public void printStatus() {
        state.printStatus();
    }

    @Override
    public void nextState() {
        state.next(this);
    }

    @Override
    public void prevState() {
        state.prev(this);
    }

    @Override
    public void setState(BasePackageState newState) {
        state = newState;
    }

    @Override
    public int getWeight() {
        return WEIGHT;
    }

    @Override
    public BasePackageState getState() {
        return state;
    }

    @Override
    public String toString() {
        return String.format("BigPackage{state:%s}", state);
    }
}
