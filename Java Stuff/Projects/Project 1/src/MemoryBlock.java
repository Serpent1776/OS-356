public class MemoryBlock {
    String name;
    int bytes;

    public MemoryBlock(String name, int bytes) {
        this.name = name;
        this.bytes = bytes;
    }
    public int getBytes() {
        return bytes;
    }
    public String getName() {
        return name;
    }
    public boolean equals(MemoryBlock other) {
        return this.name.equals(other.name) && this.bytes == other.bytes;
    }
}
