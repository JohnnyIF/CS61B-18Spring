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

   public int subOne(int i){
        if (i == 0){
            return capacity - 1;
        }
        return i-1;
   }

    public int addOne(int i){
        if (i == capacity - 1){
            return 0;
        }
        return i+1;
    }
    public int sub(int i, int factor){
        if (i < factor){
            return capacity - factor + i;
        }
        return i-1;
    }

    public boolean isFull(){
        return capacity > size;
    }

    public void resize(int cap){
        T[] newItems = (T[]) new Object[cap];
        int i = 0;

        while(subOne(nextFirst) != nextLast){
            newItems[i] = items[subOne(nextFirst)];
            i += 1;
            nextFirst = subOne(nextFirst);
        }

        capacity = cap;
        nextFirst = i+1;
        nextLast = cap - 1;
        items = newItems;
    }

    public void addFirst(T item){
        if (isFull()){
            resize(capacity * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst += 1;
    }

    public void addLast(T item){
        if (isFull()){
            resize(capacity * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast -= 1;
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
        for( int i = 0; i < nextFirst; i++){
            System.out.println(items[i]);
        }
        for(int i= capacity-1; i > nextLast; i--){
            System.out.println(items[i]);
        }
    }
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        nextFirst = subOne(nextFirst);
        T first = items[nextFirst];
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
        nextLast = addOne(nextLast);
        T last = items[nextLast];
        size -= 1;
        if (size < capacity / 2){
            resize(capacity / 2);
        }
        return last;
    }
    public T get(int index){
        if(index >= size){
            return null;
        }
        int target = sub(nextFirst-1, index);
        return items[target];
    }

}
