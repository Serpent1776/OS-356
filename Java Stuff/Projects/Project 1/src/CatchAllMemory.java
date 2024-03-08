//currently a wip that may or may not be implemented in CatchAllMemoryStorage
public class CatchAllMemory {
    private class Node {
        Integer num;
        Node next;
        
        public Node(Integer num, Node next) {
            this.num = num;
            this.next = next;
        }
    }
    int memoryLimit;
    Node head;
    Node feet;
    public CatchAllMemory(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.head = null;
        this.feet = null;
    }
    public int getMemoryLimit() {
        return memoryLimit;
    }
    public void addtohead(int num) {
        Node Newn = new Node(num, head);
        Node temp = head;
        head = Newn;
        Newn.next = temp;
    }
    public void addtofeet(int num) {
        Node Newn = new Node(num, null);
        feet.next = Newn;
        feet = Newn;
    }
    public void add(int pos, int num) {
        Node n1 = head;
        for(int i = 0; i < pos; i++) {
            n1 = n1.next;
        }
        Node newN = new Node(num, n1.next);
        n1.next = newN;
    }
    public void flip(int pos) {
        Node n1 = head;
        for(int i = 0; i < pos; i++) {
            n1 = n1.next;
        }
        n1.num = -n1.num;
    }
    public void addjust(int pos, int num) {
        add(pos - 1, num);
        Node n1 = head;
        for(int i = 0; i < pos; i++) {
            n1 = n1.next;
        }
        n1.num -= num;
    }
    public int get(int pos) {
        Node n1 = head;
        for(int i = 0; i < pos; i++) {
            n1 = n1.next;
        }
        return n1.num;
    }
}
