package itma.task2;

public class SkewHeap {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public SkewHeap() {
        root = null;
    }

    public void insert(int data) {
        root = merge(root, new Node(data));
    }

    public int extractMin() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = root.data;
        root = merge(root.left, root.right);
        return min;
    }

    private Node merge(Node h1, Node h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }

        //  с меньшим корнем -> корнем нового дерева
        if (h1.data > h2.data) {
            // меняемм местами деревья h1 и h2
            Node temp = h1;
            h1 = h2;
            h2 = temp;
        }

        // слияние правого поддерева с левым
        h1.right = merge(h1.right, h2);
        //  меняем местами
        Node temp = h1.left;
        h1.left = h1.right;
        h1.right = temp;

        return h1;
    }


    public void printHeap() {
        printHeap(root, "", true);
    }

    private void printHeap(Node node, String indent, boolean isLeft) {
        if (node != null) {
            System.out.println(indent + (isLeft ? "L---- " : "R---- ") + node.data);
            printHeap(node.left, indent + (isLeft ? "|     " : "      "), true);
            printHeap(node.right, indent + (isLeft ? "|     " : "      "), false);
        }
    }


}
