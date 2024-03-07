/* This program generates 100 random numbers and searches for how many
   times it repeats the same number twice in a row (i.e. where the previous
   and current numbers are equal.  But it keeps crashing. */
import java.util.*;

public class Debugging4 {
    public static void main(String[] args) {
        Random random = new Random();
        random.setSeed(0);
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++)
            numbers.add(random.nextInt(10));
        int count = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) == numbers.get(i+1))
                count++;
        }
        System.out.println(count);
    }
}