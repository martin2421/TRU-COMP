import java.util.Arrays;

public class ArrayBST {

     int[] tree;
     int root;

     public ArrayBST(int size) {
          tree = new int[size];
          Arrays.fill(tree, -1); // fill array with -1 (indicates it's empty)
          root = 0;
     }

     public void insert(int item) {
          recursiveInsert(item, root);
     }

     private void recursiveInsert(int item, int root) {
          
          if (root >= tree.length) {
               return;
          }

          // insert root
          if(tree[root] == -1) {
               tree[root] = item;
          } 
          else {

               // item is greater than root (goes right)
               if (item >= tree[root]) {
                    recursiveInsert(item, 2 * (root + 1));
               }

               // item is less than root (goes left)
               if (item < tree[root]) {
                    recursiveInsert(item, 2 * root + 1);
               }

          }

     }

     // helper method
     private int powerOf2(int power) {
          return (1 << power);
     }

     public String toString() {

          String result = "";
 
          int currentLevel = 0;
          int childrenPerLevel = powerOf2(currentLevel);

          for (int i = 0; i < tree.length; i++) {
               if(i == childrenPerLevel - 1) {
                    result += "\n";
                    currentLevel++;
                    childrenPerLevel = powerOf2(currentLevel);
               }
               result += " " + tree[i];
          }

          return result;
     }

}
