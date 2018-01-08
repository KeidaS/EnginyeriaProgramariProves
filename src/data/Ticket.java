package data;

public class Ticket {
    String name;
    public Ticket (String name) {
        this.name=name;
    }
    public boolean equals(Object other) {
        if (other.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + name.hashCode();
        return hash;
    }
    @Override
    public String toString() {
        return name.toString();
    }
}
