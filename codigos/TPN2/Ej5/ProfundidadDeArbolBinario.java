package TPN2.Ej5;
import clases_tps.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;


public class ProfundidadDeArbolBinario {
    public BinaryTree<Integer> root;

    public void crear_arbol() {
        ArrayList<Integer> datos = new ArrayList<>(Arrays.asList(10,2,3,5,4,9,8,7,8,5,6,12,8,2,1));
		ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
        for(Integer x : datos) {
			// System.out.print(x + " ");
			lista.add(new BinaryTree<>(x));
		}
		for(int i = 0; i < lista.size(); i++) {
			if(2*i+1 < lista.size()) {
				lista.get(i).addLeftChild(lista.get(2*i+1));
			}
			if(2*i+2 < lista.size()) {
				lista.get(i).addRightChild(lista.get(2*i+2));
			}
		}
        root = lista.get(0);
    }

	public void InOrden(BinaryTree<Integer> cur) {
		if(cur.hasLeftChild()) InOrden(cur.getLeftChild());
		System.out.print(cur.getData() + " ");
		if(cur.hasRightChild()) InOrden(cur.getRightChild());

	}

	private int treeTraversal(BinaryTree<Integer> node, int cur_prof, int prof) {
		Integer ans = 0;
		if(cur_prof == prof) return node.getData(); // No es necesarios seguir buscando mas profundo
		if(node.hasLeftChild()) ans += this.treeTraversal(node.getLeftChild(), cur_prof + 1, prof);
		if(node.hasRightChild()) ans += this.treeTraversal(node.getRightChild(), cur_prof + 1, prof);
		return ans;
	}

    public int sumaElementosProfundidad(int p) {
        return this.treeTraversal(this.root,0,p);
    }

    public static void main(String[] args) {
        ProfundidadDeArbolBinario tree = new ProfundidadDeArbolBinario();
		tree.crear_arbol();
		// Imprimimos el inorder para chequear que este bien construido el arbol
        tree.InOrden(tree.root);
		Integer prof = 2; // Profundidad deseada
		System.out.println("\nSuma elementos profundidad: " + tree.sumaElementosProfundidad(prof));
    }
}
