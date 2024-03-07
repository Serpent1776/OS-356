/* This program generates 100 random numbers and searches for how many
   5s were generated.  But it always returns 100, which isn't correct. */

import java.util.*;

public class Debugging5 {
    public static void main(String[] args) {
        Random random = new Random();
        random.setSeed(0);
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i=0; i<100; i++)
            numbers.add(random.nextInt(10));

        int count = 0;
        for (int i=0; i < 100; i++) {
            if (numbers.get(i) == 5) {
                count++;
            }
        }
        System.out.println(count);
    }
}
