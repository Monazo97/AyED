package clases_tps;
import java.util.ArrayList;

public class BinaryTree <T> {
	
	private T data;
	private BinaryTree<T> leftChild;   
	private BinaryTree<T> rightChild; 

	
	public BinaryTree() {
		super();
	}

	public BinaryTree(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Preguntar antes de invocar si hasLeftChild()
	 * @return
	 */
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}
	/**
	 * Preguntar antes de invocar si hasRightChild()
	 * @return
	 */
	public BinaryTree<T> getRightChild() {
		return this.rightChild;
	}

	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild = child;
	}

	public void addRightChild(BinaryTree<T> child) {
		this.rightChild = child;
	}

	public void removeLeftChild() {
		this.leftChild = null;
	}

	public void removeRightChild() {
		this.rightChild = null;
	}

	public boolean isEmpty(){
		return (this.isLeaf() && this.getData() == null);
	}

	public boolean isLeaf() {
		return (!this.hasLeftChild() && !this.hasRightChild());

	}
		
	public boolean hasLeftChild() {
		return this.leftChild!=null;
	}

	public boolean hasRightChild() {
		return this.rightChild!=null;
	}
	@Override
	public String toString() {
		return this.getData().toString();
	}

	public int contarHojas() {
		if(this.isLeaf()) return 1;
		else {
			int ans = 0;
			if(this.hasLeftChild()) {
				ans += this.getLeftChild().contarHojas();
			}
			if(this.hasRightChild()) {
				ans += this.getRightChild().contarHojas();
			}
			return ans;
		}
	}
    	 
    public BinaryTree<T> espejo(){
		BinaryTree<T> aux = this.getLeftChild();
		this.leftChild = this.getRightChild();
		this.rightChild = aux;
		if(this.hasLeftChild()) this.getLeftChild().espejo();
		if(this.hasRightChild()) this.getRightChild().espejo();
		return null;
    }

	// 0<=n<=m
	public void entreNiveles(int n, int m, int cur_level){
		if(cur_level >= n && cur_level <= m) {
			System.out.print(this.getData() + " ");
		}
		if(this.hasLeftChild()) this.getLeftChild().entreNiveles(n, m, cur_level+1);
		if(this.hasRightChild()) this.getRightChild().entreNiveles(n, m, cur_level+1);
   	}

	public static void main(String[] args) {
        ArrayList<BinaryTree<Integer>> lista = new ArrayList<>();
		for(int i = 1; i <= 6; i++) {
			lista.add(new BinaryTree<>(i));
		}
		for(int i = 0; i < 6; i++) {
			if(2*i+1 < lista.size()) {
				lista.get(i).addLeftChild(lista.get(2*i+1));
			}
			if(2*i+2 < lista.size()) {
				lista.get(i).addRightChild(lista.get(2*i+2));
			}
		}
		System.out.println("Cantidad de hojas: " + lista.get(0).contarHojas());
		lista.get(0).espejo();
		lista.get(0).entreNiveles(0, 5, 0);
	}
		
}


