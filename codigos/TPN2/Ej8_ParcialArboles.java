package TPN2;
import clases_tps.BinaryTree;
import java.util.ArrayList;
import java.util.Scanner;

public class Ej8_ParcialArboles {
        
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

    public boolean esPrefijo(BinaryTree<Integer> root1, BinaryTree<Integer> root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 != null && root2 == null) return false;
        if(root1 == null) return true;
        System.out.println("Comparando " + root1.getData() + " y " + root2.getData());
        if(root1.getData() != root2.getData()) return false;
        return esPrefijo(root1.getLeftChild(), root2.getLeftChild()) && esPrefijo(root1.getRightChild(), root2.getRightChild());
    }
    public  static void main(String[] args) {
        Ej8_ParcialArboles arbol = new Ej8_ParcialArboles();
        Scanner sc = new Scanner(System.in);
        BinaryTree<Integer> root1 = arbol.crear_arbol(sc);
        BinaryTree<Integer> root2 = arbol.crear_arbol(sc);
        sc.close();
        // System.out.println("In-order traversal:");
        // arbol.inOrder(root1); System.out.println();
        // arbol.inOrder(root2); System.out.println();
        boolean ans = arbol.esPrefijo(root1, root2);
        System.out.println("Es prefijo: " + ans);
    }

    
}
