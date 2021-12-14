package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.state.BoxState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderedState implements BoxState {
    private static final Logger log = LogManager.getLogger(OrderedState.class);

    @Override
    public void next(Box box) {
        box.setState(new DeliveredState());
    }

    @Override
    public void prev(Box box) {
        box.setState(new UnorderedState());
    }

    @Override
    public void printStatus(Box box) {
        log.log(Level.INFO, "{} was ordered, not delivered to office yet.", box);
    }

    @Override
    public String toString() {
        return "ordered";
    }
}
