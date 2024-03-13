/* Problem: Class doesn't implement a first fit for memory because of how the linked list works.
 * Solution 1 (not implemented): making the memory block a list and marking it as removed instead of actually removing it.
 * Solution 2 (implemented and works!): using a separate list class to keep track of the catch all memory that is used
 * and change remove to suit it. 
 * Removed element's number would be negative instead of being gone from the separate list.
 * implementation would be active when an element is removed in the middle of the linked list.
 * NOT the start or the end of the linked list.
 */
public class CatchAllMemoryStorage {
    protected class Block {
        MemoryBlock block; 
        Block next;
        int leftovers;

        public Block(MemoryBlock block, Block next) {
            this.block = block;
            this.leftovers = 0;
            this.next = next;
        }
        @SuppressWarnings("unused")
        public boolean equals(Block other) {
            return block.equals(other.block); //assuming that there are no blocks of the same name
        }
        public MemoryBlock getBlock() {
            return block;
        }
    }
    Block first;
    Block last;
    CatchAllMemory memoryList;
    int size;
    //int memoryTotal; depreciated
    public Block getLast() {
        return last;
    }

    public CatchAllMemoryStorage(int memoryLimit) {
        this.first = null;
        this.last = null;
        this.memoryList = new CatchAllMemory(memoryLimit);
        this.size = 0;
    }
    
    public void add(MemoryBlock block) throws MemoryException {
        if((memoryList.getMemoryTotal() - 1 + block.getBytes()) > memoryList.getMemoryLimit() - 1 && (block.getBytes() > memoryList.findLowest())) {
            throw new MemoryException("Memory block " + block.getName() + "'s storage would go over the memory limit!");
        } else if(first == null) { //starter case
            Block newBlock = new Block(block, first);
            Block temp = first;
            first = newBlock;
            first.next = temp;
            last = newBlock;
            memoryList.startadd(block.getBytes());
            
        } else { //first fit algorithm implementation
            int target = this.memoryList.firstFitFind(block.getBytes());
            Block b1 = first;
            int pos = 0;
            while(b1.next != null && pos + b1.next.leftovers < target && pos + b1.block.getBytes() < target) {
                pos += b1.block.getBytes() + b1.next.leftovers;        
                b1 = b1.next;
            }
            if(b1.next != null) {
            pos += b1.block.getBytes();
            memoryList.add(pos, block.getBytes());
            b1.next.leftovers -= block.getBytes();
            Block newBlock = new Block(block, b1.next);
            b1.next = newBlock;  
            } else {
                Block newBlock = new Block(block, null);
                last.next = newBlock;    
                last = newBlock;
                memoryList.addtoend(block.getBytes());
            }  
    }
    size++;
}
    public void remove(String s) throws MemoryException {
        if(first.block.getName().equals(s)) { //remove front case

            memoryList.removeFront(first.block.getBytes());
            size--;
            first = first.next;
        } else if(last.block.getName().equals(s)) { //remove last case
            memoryList.removeEnd(last.block.getBytes());
            size--;
            Block b1 = first;
            while(b1.next.next != null) {b1 = b1.next;}
            last = b1;
            last.next = null;
        } else { /*"remove" case  
        * but removes it from the linked list)
        */
        Block b1 = first;
        boolean found = false;
        int pos = -1;
        while(b1.next != null && !found) {
            pos += b1.block.getBytes() + b1.leftovers;
            if(b1.next.block.getName().equals(s)) {
                Block next2 = b1.next.next;
                next2.leftovers += b1.next.block.getBytes() + b1.next.leftovers;
                memoryList.remove(pos, next2.leftovers);
                b1.next = next2;
                size--;
                found = true;
                break;
            }
            b1 = b1.next;
        }
        if(!found) {throw new MemoryException("Memory Block " + s + " not found.");}
    }
    }
    public int getMemoryLimit() {
        return memoryList.getMemoryLimit();
    }
    public CatchAllMemory getMemoryList() {
        return memoryList;
    }
    public String memoryListStarDash() {
        return memoryList.toString();
    }
    @Override
    public String toString() {
        String s = "{";
        //char[] starDash = memoryList.getStarDash();
        int catchMem = 0;
        Block b1 = first;
        int pos = -1;
        while(b1 != null) {
            pos += b1.block.getBytes() + b1.leftovers;
            s += b1.block.name + ":";
            s += " (" + catchMem + ", " + (pos) + ")";
            if(b1.next != null) {
                catchMem += b1.block.getBytes() + b1.next.leftovers;    
                s += ", ";
            } else {
                catchMem += b1.block.getBytes();
            }
            b1 = b1.next;     
        }
        s += "}";
        return s;
    }
}