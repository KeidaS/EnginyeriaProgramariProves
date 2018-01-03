public class Currency {

    String name;

    public Currency(String name) {
        this.name = name;
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
        hash = hash * 31 + name.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
