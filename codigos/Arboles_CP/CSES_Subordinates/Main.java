import java.io.*;
import java.util.*;

class FastOutput {
    private final PrintWriter out;

    public FastOutput() {
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    public void print(Object o) {
        out.print(o);
    }

    public void println(Object o) {
        out.println(o);
    }

    public void println() {
        out.println();
    }

    public void flush() {
        out.flush();
    }

    public void close() {
        out.close();
    }
}

class Reader {
    private final int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    // Reads the next integer from input
    public int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        return neg ? -ret : ret;
    }

    // Reads the next byte from the buffer
    private byte read() throws IOException {
        if (bufferPointer == bytesRead) fillBuffer();
        return buffer[bufferPointer++];
    }

    // Fills the buffer with new data
    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1;
    }
}

class GeneralTree<T> {
    private T data;
    private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();
    
    public GeneralTree(T data) {
        this.data = data;
    }
    
    public GeneralTree(T data,List<GeneralTree<T>> children){
        this(data);
        this.children = children;
    }
    
    public boolean hasChildren() {
        return children!=null && !children.isEmpty();
    }
    
    public void setChildren(List<GeneralTree<T>> children) {
        if (children != null)
        this.children = children;
    }
    
    public List<GeneralTree<T>> getChildren() {
        return this.children;
    }
    
    public void addChild(GeneralTree<T> child) {
        getChildren().add(child);
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public boolean isLeaf() {
        return !hasChildren();
    }
    
    public boolean isEmpty() {
        return data==null && !this.hasChildren();
    }
    
    public void removeChild(GeneralTree<T> child) {
        if (this.hasChildren()) {
            children.remove(child);
        }
    }

    public void treeTraversal(boolean[] visited, int[] count) {
        int cur_node = (int)this.getData();
        count[cur_node] = 1; // Initialize count for the current node
        visited[cur_node] = true; // Mark the current node as visited
        List<GeneralTree<T>> children = this.getChildren();
        for (GeneralTree<T> child : children) {
            int next_node = (int)child.getData();
            if(visited[next_node] == true) continue;
            child.treeTraversal(visited, count);
            count[cur_node] += count[next_node];
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        // FastOutput out = new FastOutput();
        PrintWriter out = new PrintWriter(System.out);
        int n = sc.nextInt(); // Read the number of elements
        List<GeneralTree<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new GeneralTree<>(i)); // Initialize each node with its index
        }
        for (int i = 1; i < n; i++) {
            int parent = sc.nextInt()-1; // Read the parent index
            tree.get(parent).addChild(tree.get(i)); // Add child to parent
        }

        boolean[] visited = new boolean[n]; // Initialize visited array
        int[] count = new int[n]; // Initialize count array
        for (int i = 0; i < n; i++) {
            visited[i] = false; // Mark all nodes as unvisited
            count[i] = 0; // Initialize count for each node
        }
        tree.get(0).treeTraversal(visited, count); // Start traversal from root

        for(int i = 0; i < n; i++) {
            out.print(count[i]-1 + " "); // Print the count of nodes in each subtree
            // System.out.print(count.get(i)-1 + " "); // Print the count of nodes in each subtree
        }
        out.close();
    }
}