package hw.hw3;

public abstract class ItemDistribution {
    protected int distSize;

    public abstract int getServiceTime();
}

class Uniform extends ItemDistribution {

    public Uniform(int ds) {
        distSize = ds;
    }

    public int getServiceTime() {
        int n = (int) (Math.random() * distSize) + 1;
        return n;
    }

}

class Bimodal extends ItemDistribution {

    public Bimodal(int ds) {
        distSize = ds;
    }

    public int getServiceTime() {
        int n = (int) (Math.random() * distSize) + 1;
        while (n > (distSize / 4) && n < (distSize * 3) / 4) {
            n = (int) (Math.random() * distSize) + 1;
        }
        return n;
    }

}
