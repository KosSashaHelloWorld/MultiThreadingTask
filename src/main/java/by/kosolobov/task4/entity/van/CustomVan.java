package by.kosolobov.task4.entity.van;

public class CustomVan extends BaseVan{
    public CustomVan(int storageLimit, int speed) {
        super(storageLimit, speed);
    }

    @Override
    public String toString() {
        return String.format("CustomVan-%d{speed:%d, storage:%d}", getId(), speed, storageLimit);
    }
}
