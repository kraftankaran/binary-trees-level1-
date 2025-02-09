import java.util.Stack;

public class tree {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        int state;
        Node node;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = (node.left == null ? "." : node.left.data) + 
                     " <-- " + node.data + " --> " + 
                     (node.right == null ? "." : node.right.data);
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {

        Integer[] arr = {5, 20, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = new Node(arr[0], null, null);

        Stack<Pair> st = new Stack<>();
        Pair rootPair = new Pair(root, 1);
        st.push(rootPair);

        int idx = 0;

        while (st.size() > 0) {
            Pair t = st.peek();

            if (t.state == 1) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    t.node.left = new Node(arr[idx], null, null);
                    Pair newPair = new Pair(t.node.left, 1);
                    st.push(newPair);
                } else {
                    t.node.left = null;
                }
                t.state++;

            } else if (t.state == 2) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    t.node.right = new Node(arr[idx], null, null);
                    Pair newPair = new Pair(t.node.right, 1);
                    st.push(newPair);
                } else {
                    t.node.right = null;
                }
                t.state++;

            } else if (t.state == 3) {
                st.pop();
            }
        }

        display(root);
    }
}
