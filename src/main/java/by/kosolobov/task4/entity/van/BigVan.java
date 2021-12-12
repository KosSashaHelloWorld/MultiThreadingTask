package by.kosolobov.task4.entity.van;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigVan extends BaseVan {
    private static final Logger log = LogManager.getLogger(BigVan.class);
    private static final int STORAGE_LIMIT = 2000;
    private static final int SPEED = 60;

    public BigVan() {
        super(STORAGE_LIMIT, SPEED);
    }

    @Override
    public String toString() {
        return String.format("BigVan-%d", getId());
    }
}
