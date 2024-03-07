import java.io.File;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        MemoryStorage theMemory = new MemoryStorage(500, 10, 5, 5);
        Scanner fileScanner = new Scanner(new File("sometext.txt"));
        init(theMemory, fileScanner);
    }
    public static void init(MemoryStorage memory, Scanner scan) {
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            if(s.contains(" allocate ")) {
                String[] sArr = s.split(" ");
                MemoryBlock mem = new MemoryBlock(sArr[0], Integer.parseInt(sArr[2]));
                try {
                memory.allocate(mem);
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else if (s.contains("free ")) {
                try {
                    memory.free(s.split(" ")[1]);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
    /*
     * Memory Allocation text file process
     * For something like: A allocate 5
     * 1. Split String by whitespace
     * 2. Make new memory block 'A' with 5 bytes
     * 3. Allocate the memory block with the allocate method
     */
}
