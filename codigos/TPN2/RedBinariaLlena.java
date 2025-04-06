package TPN2;
import java.util.ArrayList;
import java.util.Arrays;

import clases_tps.BinaryTree;

public class RedBinariaLlena {
    public BinaryTree<Integer> root;

    public void crear_arbol() {
        ArrayList<Integer> datos = new ArrayList<>(Arrays.asList(10,2,3,5,4,9,8,7,8,5,6,12,8,2,1));
		ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
        for(Integer x : datos) {
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
        this.root = lista.get(0);
    }

	private Integer tree_traversal(BinaryTree<Integer> node) {
		Integer ans = node.getData();
		Integer max_left = 0, max_right = 0;
        if(node.hasLeftChild()) max_left = tree_traversal(node.getLeftChild());
        if(node.hasRightChild()) max_right = tree_traversal(node.getRightChild());
		return ans + Math.max(max_left,max_right);
    }

	public Integer retardoReenvio() {
		Integer rta = this.tree_traversal(this.root);
		return rta;
	}

	public void InOrden(BinaryTree<Integer> cur) {
		if(cur.hasLeftChild()) InOrden(cur.getLeftChild());
		System.out.print(cur.getData() + " ");
		if(cur.hasRightChild()) InOrden(cur.getRightChild());
	}

    public static void main(String[] args) {
        RedBinariaLlena tree = new RedBinariaLlena();
		tree.crear_arbol();
        System.out.println("Mayor retardo posible: " + tree.retardoReenvio());
    }
}
