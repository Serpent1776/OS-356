//is currently implemented with CatchAllMemoryStorage.
public class CatchAllMemory {
    int memoryLimit;
    int memoryTotal;
  
    char[] starDash;

    public CatchAllMemory(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.memoryTotal = 0;
        this.starDash = new char[memoryLimit];
        for(int i = 0; i < memoryLimit; i++) {
            starDash[i] = '-';
        }
        System.out.println(findLowest());
    }
    public void startadd(int bytes) {
        for(int i = 0; i < bytes; i++) {
            starDash[i] = '*';
        }
        memoryTotal += bytes;
    }
    public void addtoend(int bytes) throws MemoryException {
        if(memoryTotal + bytes > memoryLimit) {throw new MemoryException("bytes too large!");}
        for(int i = memoryTotal - 1; i < memoryTotal + bytes; i++) {
            starDash[i] = '*';
        }
        memoryTotal += bytes;
    }
    public void add(int pos, int bytes) {
        for(int i = pos; i < pos + bytes; i++) {
            starDash[i] = '*';
        }
    }
    public void removeFront(int bytes) { //CW: HUGE LINEAR TIME & MEMORY COST, but the only way I know how to do it lol
        int counter = 0;
        for(int i = 0; i < bytes; i++) {
            counter++;
        }
        char[] newStarDash = new char[memoryLimit];
        for(int i = counter; i < memoryLimit; i++) {
            newStarDash[i - counter] = starDash[i];
        }
        for(int i = memoryLimit - counter; i < memoryLimit; i++) {
            newStarDash[i] = '-';
        }
        starDash = newStarDash;
        memoryTotal -= bytes;
    }
    public void removeEnd(int bytes) {
        for(int i = memoryTotal; i > memoryTotal - bytes; i--) {
            starDash[i] = '-';
        }
        memoryTotal -= bytes;
    }
    public int findLowest() {

        int multiDash = 0;
        int highestChain = memoryLimit - memoryTotal;
        int pos = 0;
        while(pos < memoryLimit) {
            if(starDash[pos] == '-') {
                multiDash++;
            } else {
                if(multiDash > highestChain) {
                    highestChain = multiDash;
                }
                multiDash = 0;
            }
            pos++;
        }
        return highestChain;
    }
    public int getMemoryLimit() {
        return memoryLimit;
    }
    public int getMemoryTotal() {
        return memoryTotal;
    }
    public int getMemoryDif() {
        return memoryLimit - memoryTotal;
    }
    //finds the firstfit spot for a given amount of bytes! returns the position!
    public int firstFitFind(int bytes) {
        int pos = 0;
        int multiDash = 0;
        while(pos < memoryLimit && multiDash < bytes) {
            if(starDash[pos] == '-') {
                multiDash++;
            } else {
                multiDash = 0;
            }
            pos++;
        }
        return pos - bytes;
    }
    public char[] getStarDash() { 
        return starDash;
    }
    public String toString() { //but, this is instant!
        return new String(starDash);
    }
    public void remove(int pos, int bytes) {
        for(int i = pos + bytes; i > pos; i--) {
            starDash[i] = '-';
        }
    }
    
}