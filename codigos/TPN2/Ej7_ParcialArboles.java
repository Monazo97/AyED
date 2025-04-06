package TPN2;
import java.util.ArrayList;
import java.util.Scanner;

import clases_tps.BinaryTree;

public class Ej7_ParcialArboles {
    BinaryTree<Integer> root = new BinaryTree<>();
    
    public void crear_arbol() {
		int [] datos = new int[100];
        int [] posiciones = {0, 1, 2, 3, 4, 5, 7, 9, 10, 12, 25};
        int [] valores = {2, 7, -5, 23, 6, 19, -3, 55, 11, 4, 18};
        for(int i = 0; i < posiciones.length; i++) {
            datos[posiciones[i]] = valores[i];
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
        root = lista.get(0);
    }

    public void inOrder(BinaryTree<Integer> node) {
        if(node.hasLeftChild()) this.inOrder(node.getLeftChild());
        System.out.print(node.getData() + " ");
        if(node.hasRightChild()) this.inOrder(node.getRightChild());
    }
    
    private BinaryTree<Integer> findNode(BinaryTree<Integer> node, int num) {
        if (node == null) return null;
        if (node.getData() == num) return node;
        BinaryTree<Integer> leftResult = findNode(node.getLeftChild(), num);
        if (leftResult != null) return leftResult;
        return findNode(node.getRightChild(), num);
    }

    private int countUniqueChild(BinaryTree<Integer> node) {
        int count = (node.hasLeftChild() ^ node.hasRightChild()) ? 1 : 0;
        if (node.hasLeftChild()) {
            count += countUniqueChild(node.getLeftChild());
        }
        if (node.hasRightChild()) {
            count += countUniqueChild(node.getRightChild());
        }
        return count;
    }

    public boolean isLeftTree(int num) {
        BinaryTree<Integer> numNode = findNode(root, num);
        if(numNode == null) return false;
        int countLeft = -1, countRight = -1;
        if(numNode.hasLeftChild()) countLeft = countUniqueChild(numNode.getLeftChild());
        if(numNode.hasRightChild()) countRight = countUniqueChild(numNode.getRightChild());
        return countLeft > countRight;
    }

    public static void main(String[] args) {
        Ej7_ParcialArboles tree = new Ej7_ParcialArboles();
        tree.crear_arbol();
        // Imprimimos el inorder para chequear que este bien construido el arbol
        tree.inOrder(tree.root);
        System.out.println();

        int num;
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        System.out.println(tree.isLeftTree(num) ? "El nodo " + num + " es left tree." : "El nodo " + num + " NO es un nodo izquierdo.");
    }
}
