package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.state.BasePackageState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReceivedState implements BasePackageState {
    private static final Logger log = LogManager.getLogger(ReceivedState.class);

    @Override
    public void next(BasePackage pkg) {
        log.log(Level.WARN, "Package already received.");
    }

    @Override
    public void prev(BasePackage pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public void printStatus() {
        log.log(Level.INFO, "Package received");
    }

    @Override
    public String toString() {
        return String.format("received");
    }
}
