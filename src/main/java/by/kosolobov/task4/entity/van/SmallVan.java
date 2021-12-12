package by.kosolobov.task4.entity.van;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmallVan extends BaseVan {
    private static final Logger log = LogManager.getLogger(SmallVan.class);
    private static final int STORAGE_LIMIT = 200;
    private static final int SPEED = 120;

    public SmallVan() {
        super(STORAGE_LIMIT, SPEED);
    }

    @Override
    public String toString() {
        return String.format("SmallVan-%d", getId());
    }
}
