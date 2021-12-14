package by.kosolobov.task4.entity.van;

import by.kosolobov.task4.entity.LogisticBase;
import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.sevice.VanService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static by.kosolobov.task4.entity.van.VanType.DEFAULT;

public class Van extends Thread {
    private static final Logger log = LogManager.getLogger(Van.class);
    private static final VanService service = new VanService();
    final int storageLimit;
    final int speed;
    private static final TimeUnit unit = TimeUnit.MILLISECONDS;
    private final List<Box> storage = new ArrayList<>();
    private LogisticBase destination = null;

    public Van() {
        storageLimit = DEFAULT.getStorageLimit();
        speed = DEFAULT.getSpeed();
    }

    public Van(VanType type) {
        storageLimit = type.getStorageLimit();
        speed = type.getSpeed();
    }

    public Van(int storageLimit, int speed) {
        this.storageLimit = storageLimit;
        this.speed = speed;
    }

    public void setDestination(LogisticBase destination) {
        this.destination = destination;
    }


    /**
     * Created for testing.
     * @return subList of storage
     * @deprecated
     */
    @Deprecated (since = "it was created")
    public List<Box> getStorage() {
        return storage.subList(0, storage.size());
    }

    public boolean hasSpaceFor(Box pkg) {
        int stored = 0;
        for (Box inVan : storage) {
            stored += inVan.getWeight();
        }

        return stored + pkg.getWeight() <= storageLimit;
    }

    private void load(Box pkg) {
        if (hasSpaceFor(pkg)) {
            try {
                unit.sleep(200);
            } catch (InterruptedException e) {
                log.log(Level.ERROR, "{} was interrupted while loading {}: {}", this, pkg, e.getMessage());
            }
            storage.add(pkg);
        }
    }

    public void loadAll(List<Box> packages) {
        for (Box pkg : packages) {
            load(pkg);
        }

        for (Box pkg : storage) {
            packages.remove(pkg);
        }
    }

    public void deliverTo(LogisticBase destination) {
        setDestination(destination);
        service.execute(this);
    }

    public boolean hasPackages() {
        return !storage.isEmpty();
    }

    public Box unload() {
        if (storage.isEmpty()) {
            return null;
        }

        Box toUnload = storage.get(0);
        try {
            unit.sleep(200);
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "{} was interrupted while unloading {}: {}", this, toUnload, e.getMessage());
        }
        storage.remove(toUnload);

        return toUnload;
    }

    @Override
    public void run() {
        log.log(Level.INFO, "{} moving to {}.", this, destination);
        try {
            unit.sleep(60000 / speed);
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "{} was interrupted while moving: {}", this, e.getMessage());
        }

        log.log(Level.INFO, "{} arrived to {}.", this, destination);
        destination.notifyVanArrived(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Van van = (Van) o;

        if (storageLimit != van.storageLimit) return false;
        return speed == van.speed;
    }

    @Override
    public int hashCode() {
        int result = storageLimit;
        result = 31 * result + speed;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Van{storage:%d, speed:%d}", storageLimit, speed);
    }
}
