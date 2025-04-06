package TPN2;
import clases_tps.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ej9_ParcialArboles {
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
        List<Integer> elemento = (List<Integer>) node.getData();
        System.out.print("(" + elemento.get(0) + "," + elemento.get(1) + ") ");
        if(node.hasRightChild()) this.inOrderList(node.getRightChild());
    }

    /*
     * Recorre el arbol y devuelve un nuevo arbol con la suma de los valores de los nodos
     * y la diferencia entre el valor del nodo y el de su padre.
     * @return BinaryTree<List<Integer>> con la suma acumulada y la diferencia con el padre.
     */
    private BinaryTree<List<Integer>> treeTraversal(BinaryTree<Integer> node, int fatherValue, int cumSum) {
        BinaryTree<List<Integer>> result = new BinaryTree<>(new ArrayList<>());
        if(node.hasLeftChild()) {
            BinaryTree<List<Integer>> left = treeTraversal(node.getLeftChild(), node.getData(), cumSum + node.getData());
            result.addLeftChild(left);
        }
        if(node.hasRightChild()) {
            BinaryTree<List<Integer>> right = treeTraversal(node.getRightChild(), node.getData(), cumSum + node.getData());
            result.addRightChild(right);
        }
        ArrayList<Integer> values = new ArrayList<>();
        values.add(cumSum + node.getData());
        values.add(node.getData() - fatherValue);
        result.setData(values);
        return result;
    }

    
    public BinaryTree<?> sumAndDif(BinaryTree <Integer> root) {
        BinaryTree<List<Integer>> result = new BinaryTree<>(new ArrayList<>());
        if(root != null) {
            result = (BinaryTree<List<Integer>>) treeTraversal(root, 0, 0);
        }
        return result;
    }

    public static void main(String[] args) {
        Ej9_ParcialArboles arbol = new Ej9_ParcialArboles();
        Scanner sc = new Scanner(System.in);
        BinaryTree<Integer> root1 = arbol.crear_arbol(sc);
        arbol.inOrder(root1);
        BinaryTree<?> root2 = arbol.sumAndDif(root1);
        System.out.println("\nRecorrido en orden del nuevo arbol:");
        arbol.inOrderList((BinaryTree<?>) root2);
    }
}