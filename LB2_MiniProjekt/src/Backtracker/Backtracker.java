package Backtracker;

import java.util.ArrayList;

public class Backtracker<T> /*implements Iterator*/ {
    private ArrayList<T> stack = new ArrayList<>();

    public Backtracker() { }

    // Add an element
    public void Push(T element) {
        stack.add(element);
    }

    // Remove and return last added element
    public T Pop() {
        T element = stack.get(stack.size() - 1);
        stack.remove(element);
        return element;
    }

    /*// Is at lest one element left
    @Override
    public boolean hasNext() {
        if (stack.size() > 0)
            return true;
        return false;
    }

    // Get next element
    @Override
    public Object next() {
        return Pop();
    }*/
}
