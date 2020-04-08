public class ArrayDeque<T> {
    private T[] items;
    private int cap;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        cap = 8;
        items = (T[]) new Object[cap];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(T x) {
        extend();
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1) % cap;
        size++;
    }

    public void addLast(T x) {
        extend();
        items[nextLast] = x;
        nextLast = (nextLast + 1) % cap;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("null");
        }
        int first = (nextFirst + 1) % cap;
        for (int i = first; i != nextLast; i = (i + 1) % cap) {
            if (i == nextLast - 1) {
                System.out.println(items[i]);
            } else {
                System.out.print(items[i]);
                System.out.print(" ");
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int firstIndex = (nextFirst + 1) % cap;
        T ret = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size--;
        shrink();
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int lastIndex = (nextLast - 1) % cap;
        T ret = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        shrink();
        return ret;
    }

    public T get(int index) {
        int itemIndex = (nextFirst + 1 + index) % cap;
        return items[itemIndex];
    }

    private boolean isFull() {
        return nextFirst == nextLast;
    }

    private boolean isNeedShrink() {
        double usageRatio = size * 1.0 / cap * 1.0;
        return usageRatio < 0.25;
    }

    private void shrink() {
        if (isNeedShrink() && size / 2 > 0) {
            resize(cap / 2);
        }
    }

    private void extend() {
        if (isFull()) {
            resize(cap * 2);
        }
    }

    private void resize(int newCap) {
        T[] newItems = (T[]) new Object[newCap];
        int first = (nextFirst + 1) % cap;
        for (int i = first, j = 0; i != nextLast; i = (i + 1) % cap, j++) {
            newItems[j] = items[i];
        }
        nextFirst = newCap - 1;
        nextLast = size;
        items = newItems;
        cap = newCap;
    }
}
