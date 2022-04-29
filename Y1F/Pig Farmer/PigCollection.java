public class PigCollection {

    // Variables
    private int size;
    private int capacity = 5;
    private Pig[] pigArray;

    // Default Constructor
    public PigCollection() {
        this.pigArray = new Pig[capacity];
    }

    // Getters and Setters
    public Pig[] getPigArray() {
        return pigArray;
    }

    public void setPigArray(Pig[] pigArray) {
        this.pigArray = pigArray;
    }

    // Grows the capacity of the array
    private void grow() {
        int newCapacity = capacity * 2;
        Pig[] newArray = new Pig[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = pigArray[i];
        }
        capacity = newCapacity;
        pigArray = newArray;
    }

    // Adds a new Pig
    public void addPig(Pig pig) {
        if (size >= capacity) {
            grow();
        }
        pigArray[size] = pig;
        size++;
    }

    // Returns the # of Pigs added
    public int getPigCount() {
        return size;
    }

    // Checks if array is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns an array of all the Pigs
    public String getPigList() {

        String string = "";

        for (int i = 0; i < size; i++) {
            string += pigArray[i].getName() + ", ";
        }

        if (string != "") {
            string = "[" + string.substring(0, string.length() - 2) + "]";
        } else {
            string = "[]";
        }

        return string;
    }

}
