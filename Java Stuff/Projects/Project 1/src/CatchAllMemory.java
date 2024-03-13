//is currently implemented with CatchAllMemoryStorage.
public class CatchAllMemory {
    int memoryLimit;
    int memoryTotal;
    int lowest;
    Integer[] arr;
    int size;
    public CatchAllMemory(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        arr = new Integer[memoryLimit + 1];
        size = 0;
        lowest = 0;
    }
    public int getMemoryLimit() {
        return memoryLimit;
    }
    public int getMemoryTotal() {
        return memoryTotal;
    }
    public void addtofront(int num) {
        num -= 1;
        for(int i = 0; i < size; i++) {
        arr[i+1] = arr[i];
        }
        arr[0] = num;
        memoryTotal += num + 1;
        size++;

    }
    public void removeFront() {
        if(arr[0] < 0) {
            memoryTotal -= Math.abs(arr[0]) - 1;
            } else {
                memoryTotal -= arr[0] + 1;
            }
        arr[0] = null;
        for(int i = 0; i < size; i++) {
                Integer temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        size--;
        if(arr[0] < 0) {
            removeFront();
        }
        //System.out.println(size);
    }
    public void removeEnd() {
        if(arr[0] < 0) {
            memoryTotal -= Math.abs(arr[size - 1]) - 1;
            } else {
                memoryTotal -= arr[size - 1] + 1;
            }
        arr[size-1] = null;
        size--;
        if(arr[0] < 0) {
            removeEnd();
        }
    }
    public void addtoend(int num) {
        num -= 1;
        arr[size] = num; 
        size++;
        memoryTotal += num + 1; 
    }
    public void add(int pos, int num) {
        for(int i = size - 1; i > pos; i--) { 
            //int temp = arr[i+1];
            arr[i+1] = arr[i];
            arr[i] = null;
        }
        arr[pos+1] = num;
        size++;
    }
    public void flip(int pos) {
        arr[pos] = -arr[pos] - 1;
        fuse();
        findLowest();
    }
    private void findLowest() {
        lowest = 0;
        for(int i = 0; i < size; i++) {
            if(arr[i] < lowest) {
                lowest = arr[i];
            }
        }
    }
    public int getLowest() {
        return lowest;
    }
    /*
     * fuse() fuses adjacent negative numbers to into one in the array.
     */
    private void fuse() {
        for(int i = 1; i < size-1; i++) {
            if(arr[i] < 0 && arr[i+1] < 0) {
                arr[i] += arr[i+1];
                arr[i+1] = null;
                for(int u = i+1; u < size+1; u++) {
                    Integer temp = arr[u];
                    arr[u] = arr[u+1];
                    arr[u+1] = temp;
                }
                size--;
            }
        }
    }
    /*
     * addjust() is punny, it adds in a number at a position (where a negative number is)
     * and then adjusts the negative number respectively.
     */
    public void addjust(int pos, int num) {
       if(arr[pos] < 0) {
        if(arr[pos] + num == 0) {
            arr[pos] = num - 1;
            
        } else {
            add(pos - 1, num);
            pos++;
            arr[pos] += num;
        }
        findLowest();
    }
    }
    public int get(int pos) {
        return arr[pos];
    }
    /*
     * starDash makes the stars and dashes for catch all memory. * used, - free.
     */
    public String starDash() {
        String star = "";
        for(int i = 0; i < size; i++) {
            if(arr[i] > 0) {
            for(int a = 0; a < arr[i]; a++) {
            star += "*";
            }
        } else {
                for(int a = 0; a > arr[i]; a--) {
                star += "-";
            }
        }
          }
          for(int i = 0; i < (memoryLimit - memoryTotal); i++) {
            star += "-";
          }
        return star;
    }
}
