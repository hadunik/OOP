import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyStack {
    @Test
    public void simpleTest() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.count());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.count());
        assertEquals(1, stack.pop());
        assertEquals(0, stack.count());
    }

    @Test
    public void stackPushTest() {
        MyStack<Integer> stackForPush = new MyStack<>();
        stackForPush.push(5);
        stackForPush.push(7);

        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(3);
        stack.pushStack(stackForPush);

        assertEquals(4, stack.count());
        assertEquals(7, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void stackPopTest() throws Exception {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        MyStack<Integer> newStack = stack.popStack(3);
        assertEquals(3, newStack.count());
        assertEquals(1, stack.count());
        assertEquals(1, stack.pop());
        assertEquals(7, newStack.pop());
        assertEquals(5, newStack.pop());
        assertEquals(3, newStack.pop());
    }

    @Test
    public void popTestOnException(){
        MyStack<Integer> stack = new MyStack<>();
        Throwable thrown = assertThrows(EmptyStackException.class, () -> {
            int ans = stack.pop();
        });
    }

    @Test
    public void popStackTestOnException(){
        MyStack<Integer> stack = new MyStack<>();
        Throwable thrown = assertThrows(Exception.class, () -> {
            MyStack<Integer> ans = stack.popStack(5);
        });
        String exc = "the number of elements in the stack is less than you want to take";
        assertEquals( exc, thrown.getMessage());
    }
}
