package core.domain;

public class Item {

    private int mainKey;
    private String name;
    private Long pricePerOne;
    private Boolean isImperialManufacturable;
    private int count;

    public Item(int mainKey) {
        this.mainKey = mainKey;
        this.isImperialManufacturable = false;
    }

    public Item(int mainKey, Boolean isImperialManufacturable) {
        this.mainKey = mainKey;
        this.isImperialManufacturable = isImperialManufacturable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "mainKey=" + mainKey +
                ", name='" + name + '\'' +
                ", pricePerOne=" + pricePerOne +
                ", isImperialManufacturable=" + isImperialManufacturable +
                '}';
    }

    public Long getPricePerOne() {
        return pricePerOne;
    }

    public int getMainKey() {
        return mainKey;
    }

    public String getName() {
        return name;
    }

    public Boolean getImperialManufacturable() {
        return isImperialManufacturable;
    }

    public int getCount() {
        return count;
    }
}
