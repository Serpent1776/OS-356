/* Problem: Class doesn't implement a first fit for memory because of how the linked list works.
 * Solution 1: making the memory block a list and marking it as removed instead of actually removing it.
 * Solution 2: using a separate list class (wip rn) to keep track of the catch all memory that is used
 * and change remove to suit it. 
 * Removed element's number would be negative instead of being gone from the list.
 */
public class CatchAllMemoryStorage {
    private class Block {
        MemoryBlock block; 
        Block next;

        public Block(MemoryBlock block, Block next) {
            this.block = block;
            this.next = next;
        }
        public boolean equals(Block other) {
            return block.equals(other.block); //assuming that there are no blocks of the same name
        }
    }
    Block first;
    Block last;
    CatchAllMemory memoryLimit;
    int size;
    int memoryTotal;


    public CatchAllMemoryStorage(int memoryLimit) {
        this.first = null;
        this.last = null;
        this.memoryLimit = new CatchAllMemory(memoryLimit);
        this.size = 0; 
        this.memoryTotal = 0;
    }

    public void add(MemoryBlock block) throws MemoryException {
        if((memoryTotal + block.getBytes()) > memoryLimit.getMemoryLimit()) {
            throw new MemoryException("Memory block " + block.getName() + "'s storage would go over the memory limit!");
        }
        if(first == null) {
            Block newBlock = new Block(block, first);
            Block temp = first;
            first = newBlock;
            first.next = temp;
            last = newBlock;
        } else {
            Block newBlock = new Block(block, null);
            Block b1 = first;
            while(b1.next != null) {b1 = b1.next;}
            b1.next = newBlock;
            last = newBlock;
        }
        memoryTotal += block.getBytes();
        size++;
    }
    public void remove(String s) throws MemoryException {
        if(first.block.getName().equals(s)) {
            memoryTotal -= first.block.getBytes();
            size--;
            first = first.next;
        } else {
        Block b1 = first;
        int pos = 0;
        boolean found = false;
        while(b1.next != null && !found) {
            if(b1.next.block.getName().equals(s)) {
                memoryTotal -= b1.next.block.getBytes();
                Block b2 = b1;            
                b2.next = b2.next.next;
                size--;
                found = true;
            }
            b1 = b1.next;
            pos++;
        }
        if(!found) {throw new MemoryException("Memory Block " + s + " not found.");}
    }
    }
    public int getMemoryTotal() {
        return memoryTotal;
    }
    public int getMemoryLeft() {
        return memoryLimit.getMemoryLimit() - memoryTotal;
    }
    public int getMemoryLimit() {
        return memoryLimit.getMemoryLimit();
    }
    @Override
    public String toString() {
        String s = "{";
        int catchMem = 0;
        Block b1 = first;
        while(b1 != null) {
            s += b1.block.name + ":";
            s += " (" + catchMem + ", " + (catchMem + b1.block.bytes) + ")";
            catchMem += b1.block.bytes;
            if(b1.next != null) {
                s += ", ";
            }
            b1 = b1.next;
        }
        s += "}";
        return s;
    }
}