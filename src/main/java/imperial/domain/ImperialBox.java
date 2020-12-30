package imperial.domain;

import core.domain.Item;

public class ImperialBox implements Comparable<ImperialBox> {

    private int itemKey;
    private int amountPerBox;
    private int pricePerBox;
    private int currentMarketCostPerBox;
    private Item item;

    public ImperialBox() {
    }

    public ImperialBox(int itemKey, int amountPerBox, int currentMarketCostPerBox, Item item) {
        this.itemKey = itemKey;
        this.amountPerBox = amountPerBox;
        this.currentMarketCostPerBox = currentMarketCostPerBox;
        this.item = item;
    }

    public int getItemKey() {
        return itemKey;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmountPerBox() {
        return amountPerBox;
    }

    public int getCurrentMarketCostPerBox() {
        return currentMarketCostPerBox;
    }

    public Item getItem() {
        return item;
    }

    public void setCurrentMarketCostPerBox(int currentMarketCostPerBox) {
        this.currentMarketCostPerBox = currentMarketCostPerBox;
    }

    public int getPricePerBox() {
        return pricePerBox;
    }

    @Override
    public int compareTo(ImperialBox o) {
        return  ((o.getPricePerBox() * 100) / o.getCurrentMarketCostPerBox()) - ((this.pricePerBox * 100) / this.currentMarketCostPerBox);
    }
}
