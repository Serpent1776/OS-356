import java.io.File;
import java.util.Scanner;
public class MallocDriver {
    public static void main(String[] args) throws Exception {
        MemoryStorage theMemory = new MemoryStorage(500, 10, 5, 5);
        Scanner fileScanner = new Scanner(new File("input.txt"));
        init(theMemory, fileScanner);
        System.out.print("final looks\n" + theMemory);
    }
     /*
     * Memory Allocation text file process for init() method
     * For something like: A allocate 5
     * 1. Split String by whitespace
     * 2. Make new memory block 'A' with 5 bytes
     * 3. Allocate the memory block with the allocate method
     */
    public static void init(MemoryStorage memory, Scanner scan) throws Exception {
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            System.out.println(s);
            if(s.contains(" allocate ")) {
                String[] sArr = s.split(" ");
                MemoryBlock mem = new MemoryBlock(sArr[0], Integer.parseInt(sArr[2]));
                try {
                memory.allocate(mem);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (s.contains(" free")) {
                try {
                    memory.free(s.split(" ")[0]);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println(memory);
            System.out.println(memory.catchAll.memoryList.getMemoryTotal());
        }
    }
}