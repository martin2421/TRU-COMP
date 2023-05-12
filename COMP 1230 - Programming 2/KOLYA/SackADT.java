// Interface for a Sack object
public interface SackADT<T> {

     public void add(T element) throws FullSackException;

     public boolean isEmpty();

     public T drawItem() throws EmptySackException;

     public String toString();
     
}