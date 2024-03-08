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
    int memoryLimit;
    int size;
    int memoryTotal;


    public CatchAllMemoryStorage(int memoryLimit) {
        this.first = null;
        this.last = null;
        this.memoryLimit = memoryLimit;
        this.size = 0; 
        this.memoryTotal = 0;
    }

    public void add(MemoryBlock block) throws MemoryException {
        if((memoryTotal + block.getBytes()) > memoryLimit) {
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
            last.next = newBlock;
            last = newBlock;
        }
        size++;
        memoryTotal += block.getBytes();
    }
    public void remove(String s) throws MemoryException {
        Block b1 = first;
        int pos = 0;
        boolean found = false;
        while(b1 != null && !found) {
            if(b1.block.getName().equals(s)) {
                if(pos == 0) {
                    memoryTotal -= first.block.getBytes();
                    first = first.next;
                    size--; 
                } else {
                memoryTotal -= b1.block.getBytes();
                Block b2 = first;
                for(int i = 0; i < pos - 1; i++) {
                    b2 = b2.next;
                    b2.next = b2.next.next; 
                }
                }
                found = true;
            }
            b1 = b1.next;
            pos++;
        }
        if(!found) {throw new MemoryException("Memory Block " + s + " not found.");}
    }
    public int getMemoryTotal() {
        return memoryTotal;
    }
    public int getMemoryLeft() {
        return memoryLimit - memoryTotal;
    }
    public int getMemoryLimit() {
        return memoryLimit;
    }
}