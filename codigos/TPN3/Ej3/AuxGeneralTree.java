package TPN3.Ej3;
import clases_tps.*;
import java.util.*;

public class AuxGeneralTree {

    public static void main(String[] args) {
        GeneralTree<Integer> treeClass = new GeneralTree<>();
        List<GeneralTree<Integer>> tree = treeClass.reedTree();
        System.out.println("La altura del arbol es: " + tree.get(0).altura());
        System.out.println("El ancho del arbol es: " + tree.get(0).ancho());
        int node = 10;
        System.out.println("El nivel de " + node +  " es: " + tree.get(0).nivel(node));

    }
}