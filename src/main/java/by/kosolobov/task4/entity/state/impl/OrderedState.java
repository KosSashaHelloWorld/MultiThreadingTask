package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.state.BasePackageState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderedState implements BasePackageState {
    private static final Logger log = LogManager.getLogger(OrderedState.class);

    @Override
    public void next(BasePackage pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public void prev(BasePackage pkg) {
        log.log(Level.WARN, "Package is in its` root state.");
    }

    @Override
    public void printStatus() {
        log.log(Level.INFO, "Package ordered, not delivered to office yet.");
    }

    @Override
    public String toString() {
        return String.format("ordered");
    }
}
