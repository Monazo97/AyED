import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class FastReader {
    // BufferedReader to read input
    BufferedReader b;
  
    // StringTokenizer to tokenize input
    StringTokenizer s; 

    // Constructor to initialize BufferedReader
    public FastReader() {
        b = new BufferedReader
          (new InputStreamReader(System.in));
    }

    // Method to read the next token as a string
    String next() {
        while (s == null || !s.hasMoreElements()) {
            try {
                s = new StringTokenizer(b.readLine());
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
        return s.nextToken();
    }

    // Method to read the next token as an integer
    int nextInt() { 
        return Integer.parseInt(next()); 
    }

    // Method to read the next token as a long
    long nextLong() { 
        return Long.parseLong(next()); 
    }

    // Method to read the next token as a double
    double nextDouble() { 
        return Double.parseDouble(next()); 
    }

    // Method to read the next line as a string
    String nextLine() {
        String str = "";
        try {
            if (s.hasMoreTokens()) {
                str = s.nextToken("\n");
            } else {
                str = b.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return str;
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

    public int treeTraversal(List<Boolean> visited) {
        int count = 1;
        visited.set((int)this.getData(), true);
        for (GeneralTree<T> child : this.getChildren()) {
            if(visited.get((int)child.getData()) == true) continue;
            count += child.treeTraversal(visited);
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        FastReader sc = new FastReader();
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        
        List<GeneralTree<Integer>> tree = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            tree.add(new GeneralTree<>(i));
        }

        for(int i = 0; i < m; i++) {
            int x, y;
            x = sc.nextInt();
            y = sc.nextInt();
            tree.get(x-1).addChild(tree.get(y-1));
            tree.get(y-1).addChild(tree.get(x-1));
        }

        List<Boolean> visited = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            visited.add(false);
        }

        if(m != n-1) {
            System.out.println("NO");
        } else {
            int misma_componente = tree.get(0).treeTraversal(visited);
            if(misma_componente == n) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

}