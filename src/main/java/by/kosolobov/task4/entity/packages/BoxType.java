package by.kosolobov.task4.entity.packages;

public enum BoxType {
    EXTRA_SMALL {
        @Override
        public int getWeight() {
            return 1;
        }
    },
    SMALL {
        @Override
        public int getWeight() {
            return 5;
        }
    },
    MEDIUM {
        @Override
        public int getWeight() {
            return 25;
        }
    },
    BIG {
        @Override
        public int getWeight() {
            return 100;
        }
    },
    GIANT {
        @Override
        public int getWeight() {
            return 250;
        }
    },
    DEFAULT {
        @Override
        public int getWeight() {
            return 25;
        }
    };

    public abstract int getWeight();
}
