package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.state.BasePackageState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnorderedState implements BasePackageState {
    private static final Logger log = LogManager.getLogger(UnorderedState.class);

    @Override
    public void next(BasePackage pkg) {
        pkg.setState(new OrderedState());
    }

    @Override
    public void prev(BasePackage pkg) {
        log.log(Level.WARN, "Package is unordered.");
    }

    @Override
    public void printStatus() {
        log.log(Level.INFO, "Package is unordered");
    }

    @Override
    public String toString() {
        return "unordered";
    }
}
