import java.util.*;

public class BST {

    static class node {
        int value;
        node left_child;
        node right_child;

        node(int v, node lc, node rc) {
            this.value = v;
            this.left_child = lc;
            this.right_child = rc;
        }
    }

    public static void search(int value, LinkedList<node> tree) {
        if (tree.isEmpty()) {
            System.out.println("Tree is empty");
            return;
        }

        node curr = tree.get(0);
        while (curr != null) {
            if (value == curr.value) {
                System.out.println(value + " is found in BST");
                return;
            } else if (value < curr.value) {
                curr = curr.left_child;
            } else {
                curr = curr.right_child;
            }
        }
        System.out.println(value + " is not found in BST");
    }

    public static void insert(int node_value, LinkedList<node> tree) {
        node new_node = new node(node_value, null, null);

        if (tree.isEmpty()) {
            tree.add(new_node);
            return;
        }

        node current = tree.get(0);

        while (true) {
            if (node_value < current.value) {
                if (current.left_child == null) {
                    current.left_child = new_node;
                    return;
                } else {
                    current = current.left_child;
                }
            } else {
                if (current.right_child == null) {
                    current.right_child = new_node;
                    return;
                } else {
                    current = current.right_child;
                }
            }
        }
    }

    public static void inordertrav(node root) {
        if (root != null) {
            inordertrav(root.left_child);
            System.out.print(root.value + " ");
            inordertrav(root.right_child);
        }
    }

    public static void inorder(LinkedList<node> tree) {
        if (tree.isEmpty()) {
            System.out.println("tree is empty");
        } else {
            inordertrav(tree.get(0));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<node> tree = new LinkedList<>();

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int node_value = sc.nextInt();
            insert(node_value, tree);
        }

        int s = sc.nextInt();
        inorder(tree);
        search(s, tree);

        sc.close();
    }
}