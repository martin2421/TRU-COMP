package KOLYA;

public class HyphenException extends Exception {
     public HyphenException() {
          super("is missing one or more hyphens (-).");
     }
}