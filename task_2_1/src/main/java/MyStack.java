import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.concurrent.ExecutionException;

public class MyStack<T> {

    private int end;
    private T[] arr;

    public MyStack() {
        arr = (T[]) new Object[10];
        end = 0;
    }

    private void checkStack() {
        if (end == arr.length - 1) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    public int count() {
        return end;
    }

    public void push(T elem) {
        checkStack();
        arr[end] = elem;
        end++; //изменить потом
    }

    public void pushStack(MyStack<T> newStack) {
        for (int i = 0; i < newStack.end; i++) {
            this.push(newStack.arr[i]);
        }
    }

    public T pop() {
        if (end < 1) {
            throw new EmptyStackException();
        }
        return this.arr[--end];
    }

    private void increaseStack(int newSize) {
        if (arr.length < newSize) {
            arr = Arrays.copyOf(arr, newSize);
        }
    }

    public MyStack<T> popStack(int n) throws Exception {
        if (end < n) {
            throw new Exception("the number of elements in the stack is less than you want to take");
        }
        MyStack<T> newStack = new MyStack<>();
        newStack.increaseStack(n);
        for (int i = end - n; i < end; i++){
            newStack.push(arr[i]);
        }
        end -= n;
        return newStack;
    }

}
