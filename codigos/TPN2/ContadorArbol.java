package TPN2;
import clases_tps.BinaryTree;

import java.util.ArrayList;

public class ContadorArbol {
    public BinaryTree<Integer> root;

    public void crear_arbol(int tam) {
        ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
		for(int i = 1; i <= tam; i++) {
			lista.add(new BinaryTree<>(i));
		}
		for(int i = 0; i < tam; i++) {
			if(2*i+1 < lista.size()) {
				lista.get(i).addLeftChild(lista.get(2*i+1));
			}
			if(2*i+2 < lista.size()) {
				lista.get(i).addRightChild(lista.get(2*i+2));
			}
		}
        root = lista.get(0);
    }

    public void tree_traversal(BinaryTree<Integer> node, ArrayList<Integer> pares) {
        // preorder
        if(node.getData()%2 == 0) pares.add(node.getData());
        if(node.hasLeftChild()) tree_traversal(node.getLeftChild(), pares);
        if(node.hasRightChild()) tree_traversal(node.getRightChild(), pares);
    }

    public ArrayList<Integer> numerosPares() {
        ArrayList<Integer> pares = new ArrayList<>();
        this.tree_traversal(this.root, pares);
        return pares;
    }

    public void numerosParesInOrden(BinaryTree<Integer> cur, ArrayList<Integer> pares) {
        if(cur.hasLeftChild()) numerosParesInOrden(cur.getLeftChild(), pares);
        if(cur.getData()%2 == 0) pares.add(cur.getData());
        if(cur.hasRightChild()) numerosParesInOrden(cur.getRightChild(), pares);
    }

    public void numerosParesPostOrden(BinaryTree<Integer> cur, ArrayList<Integer> pares) {
        if(cur.hasLeftChild()) numerosParesPostOrden(cur.getLeftChild(), pares);
        if(cur.hasRightChild()) numerosParesPostOrden(cur.getRightChild(), pares);
        if(cur.getData()%2 == 0) pares.add(cur.getData());
    }

    public static void main(String[] args) {
        ContadorArbol tree = new ContadorArbol();
        tree.crear_arbol(10);
        
        // Hallamos los numeros pares en preorden
        ArrayList <Integer> pares = tree.numerosPares();
        System.out.println("Numeros pares en preorden:");
        for(int i = 0; i < pares.size(); i++) {
            System.out.print(pares.get(i) + " ");
        }

        // Hallamos los numeros pares en inorden
        System.out.println("\n---------------\nNumeros pares en inorden:");
        ArrayList <Integer> paresInOrden = new ArrayList<>();
        tree.numerosParesInOrden(tree.root,paresInOrden);
        for(int i = 0; i < paresInOrden.size(); i++) {
            System.out.print(paresInOrden.get(i) + " ");
        }
        
        // Hallamos los numeros pares en postorden
        System.out.println("\n---------------\nNumeros pares en postorden:");
        ArrayList <Integer> paresPostOrden = new ArrayList<>();
        tree.numerosParesPostOrden(tree.root,paresPostOrden);
        for(int i = 0; i < paresPostOrden.size(); i++) {
            System.out.print(paresPostOrden.get(i) + " ");
        }
    }
}
