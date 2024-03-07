/* This program generates 1000 random ordered pairs of the form (x,y) with 
   x and y ranging from 1 to 10.  It puts each pair into a list and into a
   set.  The set should get rid of duplicates.  However, when we print the
   sizes of the list and set, they both always have the same size.  So 
   something is wrong. 
 */

import java.util.*;
public class Test {
    private static class Pair {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        List<Pair> list = new ArrayList<Pair>();
        Set<Pair> uniquePairs = new HashSet<Pair>();
        Random random = new Random();

        for (int i=0; i<1000; i++) {
            Pair p = new Pair(random.nextInt(10) + 1, random.nextInt(10) + 1);
            list.add(p);
            uniquePairs.add(p);
        }
        System.out.println(list.size());
        System.out.println(uniquePairs.size());
    }
}
