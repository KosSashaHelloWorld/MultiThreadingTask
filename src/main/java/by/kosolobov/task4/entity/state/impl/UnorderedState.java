package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.state.BoxState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnorderedState implements BoxState {
    private static final Logger log = LogManager.getLogger(UnorderedState.class);

    @Override
    public void next(Box box) {
        box.setState(new OrderedState());
    }

    @Override
    public void prev(Box box) {
        log.log(Level.WARN, "{} is unordered. Have no previous state.", box);
    }

    @Override
    public void printStatus(Box box) {
        log.log(Level.INFO, "{} is unordered.", box);
    }

    @Override
    public String toString() {
        return "unordered";
    }
}
