import java.util.Arrays;

public class YeraArrayList<T> {
    private int size = 0;
    Object[] e = new Object[10];

    public <T> void add(T o) {
        size++;
        if (e.length > size) {
            e = Arrays.copyOf(e, 1 + size * 5);
        }
        e[size] = o;
    }

    public void print() {
        for (Object o : e) {
            if (o == null) {
                continue;
            }
            System.out.println(o);
        }
    }
}
