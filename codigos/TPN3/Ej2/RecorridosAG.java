package TPN3.Ej2;
import clases_tps.*;
import java.util.*;

public class RecorridosAG {
    private List<GeneralTree<Integer>> reedTree() {
        List <GeneralTree<Integer>> tree = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            tree.add(new GeneralTree<>(i));
        }
        for(int i = 1; i < n; i++) {
            int p = sc.nextInt();
            tree.get(p).addChild(tree.get(i));
        }
        sc.close();
        return tree;
    }

    private void preOrdenImpares(GeneralTree <Integer> a, Integer n, List<Integer> numerosImpares) {
        if(a == null) return;
        if(a.getData()%2 == 1 && a.getData() > n) {
            numerosImpares.add(a.getData());
        }
        for(GeneralTree<Integer> next : a.getChildren()) {
            preOrdenImpares(next, n, numerosImpares);
        }
        return; 
    }

    private void postOrdenImpares(GeneralTree <Integer> a, Integer n, List<Integer> numerosImpares) {
        if(a == null) return;
        for(GeneralTree<Integer> next : a.getChildren()) {
            postOrdenImpares(next, n, numerosImpares);
        }
        if(a.getData()%2 == 1 && a.getData() > n) {
            numerosImpares.add(a.getData());
        }
        return; 
    }

    private List<Integer> numerosImparesMayoresQuePreOrden(GeneralTree<Integer> a, Integer n) {
        List<Integer> numerosImpares = new ArrayList<>();
        this.preOrdenImpares(a, n, numerosImpares);
        return numerosImpares;
    }

    private List<Integer> numerosImparesMayoresQuePostOrden(GeneralTree<Integer> a, Integer n) {
        List<Integer> numerosImpares = new ArrayList<>();
        this.postOrdenImpares(a, n, numerosImpares);
        return numerosImpares;
    }

    private List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree<Integer> a, Integer n) {
        MyQueue<GeneralTree<Integer>> q = new MyQueue<>();
        List<Integer> numerosImpares = new ArrayList<>();

        q.enqueue(a);
        while(!q.isEmpty()) {
            GeneralTree<Integer> node = q.dequeue();
            if(node.getData()%2 == 1 && node.getData() > n) {
                numerosImpares.add(node.getData());
            }
            for(GeneralTree<Integer> nextNode : node.getChildren()) {
                q.enqueue(nextNode);
            }
        }

        return numerosImpares;
    }
    public static void main(String[] args) {
        RecorridosAG aux = new RecorridosAG();
        List<GeneralTree<Integer>> tree = aux.reedTree();
        int n = 1;
        
        // Imprimimos los impares mayores que n en preorden
        List<Integer> imparesPreOrden = aux.numerosImparesMayoresQuePreOrden(tree.get(0), n);
        System.out.println("Impares mayores que " + n + " en pre-orden:");
        for(Integer u : imparesPreOrden) System.out.print(u + " ");
        System.out.println("");
        
        // Imprimimos los impares mayores que n en postorden
        List<Integer> imparesPostOrden = aux.numerosImparesMayoresQuePostOrden(tree.get(0), n);
        System.out.println("Impares mayores que " + n + " en post-orden:");
        for(Integer u : imparesPostOrden) System.out.print(u + " ");
        System.out.println("");

        // Imprimimos los impares mayores que n por nivelees
        List<Integer> imparesPorNiveles = aux.numerosImparesMayoresQuePorNiveles(tree.get(0), n);
        System.out.println("Impares mayores que " + n + " por niveles:");
        for(Integer u : imparesPorNiveles) System.out.print(u + " ");
        System.out.println("");
        
        
    }
}