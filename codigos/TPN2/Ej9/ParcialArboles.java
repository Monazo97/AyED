package TPN2.Ej9;
import clases_tps.BinaryTree;
import clases_tps.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParcialArboles {
    public BinaryTree<Integer> crear_arbol(Scanner sc) {
        int tam = sc.nextInt();
        ArrayList<Integer> posiciones = new ArrayList<>();
        ArrayList<Integer> valores = new ArrayList<>();
        int [] datos = new int[100];
        for(int i = 0; i < tam; i++) posiciones.add(sc.nextInt());
        for(int i = 0; i < tam; i++) valores.add(sc.nextInt());
        
        for(int i = 0; i < posiciones.size(); i++) {
            datos[posiciones.get(i)] = valores.get(i);
        }
        ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            lista.add(new BinaryTree<>(datos[i]));
        }
        for(int i = 0; i < lista.size(); i++) {
            if(2*i+1 < lista.size() && lista.get(2*i+1).getData() != 0) {
                lista.get(i).addLeftChild(lista.get(2*i+1));
            }
            if(2*i+2 < lista.size() && lista.get(2*i+2).getData() != 0) {
                lista.get(i).addRightChild(lista.get(2*i+2));
            }
        }
        return lista.get(0);
    }

    public void inOrder(BinaryTree<Integer> node) {
        if(node.hasLeftChild()) this.inOrder(node.getLeftChild());
        System.out.print(node.getData() + " ");
        if(node.hasRightChild()) this.inOrder(node.getRightChild());
    }

    public void inOrderList(BinaryTree<?> node) {
        if(node.hasLeftChild()) this.inOrderList(node.getLeftChild());
        Pair<Integer,Integer> elemento = (Pair<Integer,Integer>) node.getData();
        System.out.print("(" + elemento.getFirst() + "," + elemento.getSecond() + ") ");
        if(node.hasRightChild()) this.inOrderList(node.getRightChild());
    }

    /*
     * Recorre el arbol y devuelve un nuevo arbol con la firsta de los valores de los nodos
     * y la diferencia entre el valor del nodo y el de su padre.
     * @return BinaryTree<Pair<Integer,Integer>> con la firsta acumulada y la diferencia con el padre.
     */
    private BinaryTree<Pair<Integer,Integer>> treeTraversal(BinaryTree<Integer> node, int fatherValue, int cumfirst) {
        BinaryTree<Pair<Integer,Integer>> result = new BinaryTree<>();
        if(node.hasLeftChild()) {
            BinaryTree<Pair<Integer,Integer>> left = treeTraversal(node.getLeftChild(), node.getData(), cumfirst + node.getData());
            result.addLeftChild(left);
        }
        if(node.hasRightChild()) {
            BinaryTree<Pair<Integer,Integer>> right = treeTraversal(node.getRightChild(), node.getData(), cumfirst + node.getData());
            result.addRightChild(right);
        }
        Pair<Integer,Integer> values = new Pair<>();
        values.setFirst(cumfirst + node.getData());
        values.setSecond(node.getData() - fatherValue);
        result.setData(values);
        return result;
    }

    
    public BinaryTree<?> firstAndDif(BinaryTree <Integer> root) {
        BinaryTree<Pair<Integer,Integer>> result = new BinaryTree<>();
        if(root != null) {
            result = (BinaryTree<Pair<Integer,Integer>>) treeTraversal(root, 0, 0);
        }
        return result;
    }

    public static void main(String[] args) {
        ParcialArboles arbol = new ParcialArboles();
        Scanner sc = new Scanner(System.in);
        BinaryTree<Integer> root1 = arbol.crear_arbol(sc);
        arbol.inOrder(root1);
        BinaryTree<?> root2 = arbol.firstAndDif(root1);
        System.out.println("\nRecorrido inorder del nuevo arbol:");
        arbol.inOrderList((BinaryTree<?>) root2);
    }
}