package by.kosolobov.task4.entity.van;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MediumVan extends BaseVan {
    private static final Logger log = LogManager.getLogger(MediumVan.class);
    private static final int STORAGE_LIMIT = 500;
    private static final int SPEED = 90;

    public MediumVan() {
        super(STORAGE_LIMIT, SPEED);
    }

    @Override
    public String toString() {
        return String.format("MediumVan-%d", getId());
    }
}
