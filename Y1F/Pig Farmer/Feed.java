public class Feed {

    // Variables
    private boolean isFull;
    private double weight;
    private String nameFeed;

    // Getters & Setters
    public String getNameFeed() {
        return nameFeed;
    }

    public void setNameFeed(String nameFeed) {
        this.nameFeed = nameFeed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    // isFull is set to false (bin has been consumed)
    public void consume() {
        isFull = false;
    }

    // Name for the feed and set isFull to true
    Feed(String nameFeed_p) {
        nameFeed = nameFeed_p;
        isFull = true;
    }

}
