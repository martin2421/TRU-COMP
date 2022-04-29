public class FeedCollection {

    // Variables
    public static final int SIZE = 20;
    private Feed[] feedArray = new Feed[SIZE];

    // Getters & Setters
    public int getSize() {
        return SIZE;
    }

    public Feed[] getFeedArray() {
        return feedArray;
    }

    public void setFeedArray(Feed[] feedArray) {
        this.feedArray = feedArray;
    }

    // Set a Feed object to a particular location in array
    public void setFeedNumber(int location, Feed feed) {
        try {
            feedArray[location - 1] = feed;
        } catch (Exception e) {
            System.out.println("Whoops! That bin doesn't exist...");
        }
    }

    // Sets particular feed bin to empty
    public void emptyFeedNumber(int id) {
        feedArray[id].consume();
    }

    // Returns the current # of feed bins that are full
    public int getNumberOfFullBins() {
        int fullCounter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (feedArray[i] != null && feedArray[i].isFull()) {
                fullCounter++;
            }
        }
        return fullCounter;
    }

    // Returns a Feed obj if there's one available
    public Feed getNextFullBin() {
        for (int i = 0; i < SIZE; i++) {
            if (feedArray[i] != null && feedArray[i].isFull()) {
                return feedArray[i];
            }
        }
        return null;
    }

}
