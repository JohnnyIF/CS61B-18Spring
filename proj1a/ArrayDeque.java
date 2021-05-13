public class ArrayDeque<T>{
    private T[] items;
    private int start ;
    private int end ;
    private int size;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        start = 0;
        end = 1;
        size = 0;

    }
    private int cal(int i){
        return (i + items.length) % items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize(int newSize){
        T[] temp = (T[]) new Object[newSize];
        int j = 0;
        for(int i =cal(start + 1); j != size; i = cal(i+1)){
            temp[j] = items[i];
            j += 1;
        }
        items = temp;
        start = newSize - 1;
        end = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[start] = item;
        size += 1;
        start = cal(start - 1);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[end] = item;
        size += 1;
        end = cal(end + 1);
    }

    public void printDeque() {
        for (int i = cal(start + 1); i != end; i = cal(i + 1)) {
            System.out.print(items[i]);
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        start = cal(start + 1);
        T item = items[start];
        items[start] = null;
        size -= 1;
        if (items.length > 16 && size * 2 < items.length) {
            resize(size);
        }
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        end = cal(end - 1);
        T item = items[end];
        items[end] = null;
        size -= 1;
        if (items.length > 16 && size * 2 < items.length) {
            resize(size);
        }
        return item;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        return items[cal(start + index + 1)];
    }


}