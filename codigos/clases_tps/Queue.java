package clases_tps;

import java.util.ArrayList;

public class Queue <T> {
    ArrayList<T> queue = new ArrayList<>();

    public Queue() {
        super();
    }

    public void enqueue(T element) {
        queue.add(element);
    }

    public T dequeue() {
        if (!isEmpty()) {
            return queue.remove(0);
        } else {
            return null; // or throw an exception
        }
    }

    public T head() {
        if (!queue.isEmpty()) {
            return queue.get(0);
        } else {
            return null; // or throw an exception
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public String toString() {
        String result = "[";
        for (int i = 0; i < queue.size(); i++) {
            result += queue.get(i).toString();
            if (i < queue.size() - 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
}
