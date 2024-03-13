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

        public Block(MemoryBlock block, Block next) {
            this.block = block;
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
    //Uses first fit and treats negative numbers as possible free spots for memory
    //addjust would be used here
    public void add(MemoryBlock block) throws MemoryException {
        if((memoryList.getMemoryTotal() + block.getBytes()) > memoryList.getMemoryLimit() && (block.getBytes() > -1*memoryList.getLowest())) {
            throw new MemoryException("Memory block " + block.getName() + "'s storage would go over the memory limit!");
        }
        if(first == null) { //starter case
            Block newBlock = new Block(block, first);
            Block temp = first;
            first = newBlock;
            first.next = temp;
            last = newBlock;
            memoryList.addtofront(block.getBytes());
        } else if (block.getBytes() > -1*memoryList.getLowest()) {
            Block newBlock = new Block(block, null);
            last.next = newBlock;    
            last = newBlock;
            memoryList.addtoend(block.getBytes());
        } else { //first fit algorithm implementation
            Block newBlock = new Block(block, null);
            Block b1 = first;
            boolean fit = false;
            int pos = 0;
            while(b1.next != null && !fit) {
                if(memoryList.get(pos + 1) < 0 && (memoryList.get(pos + 1) + block.getBytes()) <= 0) {
                    fit = true;
                    memoryList.addjust(pos + 1, block.getBytes());
                    break;
                }
                b1 = b1.next;
                pos++;
                if(memoryList.get(pos) < 0 && (memoryList.get(pos) + block.getBytes()) > 0) {
                    pos++;
                }
            }
             
                newBlock.next = b1.next;
                b1.next = newBlock;
            }
        size++;
    }
    public void remove(String s) throws MemoryException {
        if(first.block.getName().equals(s)) { //remove front case

            memoryList.removeFront();
            size--;
            first = first.next;
        } else if(last.block.getName().equals(s)) { //remove last case
            memoryList.removeEnd();
            size--;
            Block b1 = first;
            while(b1.next.next != null) {b1 = b1.next;}
            last = b1;
            last.next = null;
        } else { /*"remove" case 
        *(doesn't actually remove the element from the memory list (sign flips it), 
        * but removes it from the linked list)
        */
        Block b1 = first;
        int pos = 0;
        boolean found = false;
        while(b1.next != null && !found) {
            if(b1.next.block.getName().equals(s)) {

                Block noxt = b1.next.next;
                b1.next = noxt;
                memoryList.flip(pos + 1);
                size--;
                found = true;
                break;
            }
            b1 = b1.next;
            pos++;
            if(memoryList.get(pos) < 0) {
                pos++;
            }
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
        return memoryList.starDash();
    }
    @Override
    public String toString() {
        String s = "{";
        int catchMem = 0;
        Block b1 = first;
        int pos = 0;
        while(b1 != null) {
            
            if(memoryList.get(pos) > 0) {
            s += b1.block.name + ":";
            s += " (" + catchMem + ", " + (catchMem + memoryList.get(pos)) + ")";
            catchMem += memoryList.get(pos) + 1;
            if(b1.next != null) {
                s += ", ";
            }
            b1 = b1.next;
        } else {
            catchMem -= memoryList.get(pos);
        }
            pos++;
            
        }
        s += "}";
        return s;
    }
}