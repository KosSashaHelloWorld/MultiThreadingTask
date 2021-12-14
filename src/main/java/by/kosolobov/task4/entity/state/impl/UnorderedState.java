package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.state.BoxState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnorderedState implements BoxState {
    private static final Logger log = LogManager.getLogger(UnorderedState.class);

    @Override
    public void next(Box pkg) {
        pkg.setState(new OrderedState());
    }

    @Override
    public void prev(Box pkg) {
        log.log(Level.WARN, "Box is unordered. Have no previous state.");
    }

    @Override
    public void printStatus() {
        log.log(Level.INFO, "Box is unordered.");
    }

    @Override
    public String toString() {
        return "unordered";
    }
}
