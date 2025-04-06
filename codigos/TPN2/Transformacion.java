package TPN2;
import clases_tps.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;

public class Transformacion {

    public BinaryTree<Integer> root;

    public void crear_arbol() {
        ArrayList<Integer> datos = new ArrayList<>(Arrays.asList(1,2,3,0,4,5,6,0,0,0,0,7,8));
		ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
        for(Integer x : datos) {
			// System.out.print(x + " ");
			lista.add(new BinaryTree<>(x));
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

	public void InOrden(BinaryTree<Integer> cur) {
		if(cur.hasLeftChild()) InOrden(cur.getLeftChild());
		System.out.print(cur.getData() + " ");
		if(cur.hasRightChild()) InOrden(cur.getRightChild());
	}

	private Integer treeTraversal(BinaryTree<Integer> node) {
		Integer old_value = node.getData();
		Integer leftValue = 0, rightValue = 0;
		node.setData(0);
		if(node.hasLeftChild()) {
			leftValue = this.treeTraversal(node.getLeftChild());
		}
		if(node.hasRightChild()) {
			rightValue = this.treeTraversal(node.getRightChild());
		}
		node.setData(leftValue + rightValue);
		return old_value + node.getData();
	}

	public BinaryTree<Integer> suma() {
		this.treeTraversal(root);
		return root;
	}

    public static void main(String[] args) {
        Transformacion tree = new Transformacion();
		tree.crear_arbol();

		// Imprimimos el inorder para chequear que este bien construido el arbol
		tree.InOrden(tree.root);
		System.out.println();

		BinaryTree<Integer> new_root = tree.suma();
		tree.InOrden(new_root);
    }
}