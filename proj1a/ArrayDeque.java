public class ArrayDeque<T> {
    private int nextFirst;
    private int nextLast;
    private int size;
    private T[] items;
    private int capacity;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 7;
        capacity = 8;
    }

    private int modCapacity(int i) {
        return (i + capacity) % capacity;
    }

    private boolean isFull(){
        return capacity > size;
    }

    private void resize(int newSize){
        T[] temp = (T[]) new Object[newSize];
        int i = 0;

        while (modCapacity(nextLast + 1) != nextFirst){
            temp[i] = items[modCapacity(nextLast + 1)];
            i += 1;
            nextLast = modCapacity(nextLast);
        }

        capacity = newSize;
        nextFirst = i+1;
        nextLast = capacity - 1;
        items = temp;
    }

    public void addFirst(T item){
        if (isFull()){
            resize(capacity * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = modCapacity( nextFirst + 1);
    }

    public void addLast(T item){
        if (isFull()){
            resize(capacity * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = modCapacity( nextLast - 1);
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if (isEmpty()){
            return;
        }
        int j = modCapacity(nextFirst - 1);
        while (j != nextLast){
            System.out.println(items[j]);
            j = modCapacity(j - 1);
        }
    }
    public T removeFirst(){
        if (isEmpty()){
            return null;
        };
        nextFirst = modCapacity(nextFirst - 1);
        T first = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if (size < capacity / 2){
            resize(capacity / 2);
        }
        return first;
    }
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        nextLast = modCapacity(nextLast + 1);
        T last = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if (size < capacity / 2){
            resize(capacity / 2);
        }
        return last;
    }
    public T get(int index){
        int target = modCapacity(nextFirst - index - 1 );
        return items[target];
    }

    public static void main(String[] args) {
        System.out.println(-1 % 8);
    }

}
