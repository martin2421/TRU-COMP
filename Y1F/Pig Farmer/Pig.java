import java.time.LocalDate;

public class Pig {

    // Keeps track of the # of pigs that the farmer has
    private static int count = 0;

    private String name, color, pigID;
    private char sex;
    private LocalDate date;
    private boolean isFed;

    // A static public method named getCount()
    public static int getCount() {
        return count;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPigID() {
        return pigID;
    }

    public void setPigID(String pigID) {
        this.pigID = pigID;
    }

    public static void setCount(int count) {
        Pig.count = count;
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean isFed) {
        this.isFed = isFed;
    }

    // Constructor with ID, name, sex, color, date
    Pig(String pigID_p, String name_p, char sex_p, String color_p, LocalDate date_p) {
        pigID = pigID_p;
        name = name_p;
        sex = sex_p;
        color = color_p;
        date = date_p;
    }

    // Accepts an object of type Feed and calls the method consume on the object
    public void feed(Feed feedObj) {
        feedObj.consume();
    }

}
