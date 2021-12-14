package by.kosolobov.task4.entity.state;

import by.kosolobov.task4.entity.packages.Box;

public interface BoxState {
    void next(Box box);
    void prev(Box box);
    void printStatus(Box box);
}
