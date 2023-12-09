import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> result = new ArrayList<T>();

        if (root == null) {
            return result;
        }

        result.add(root.getData());

        List<T> leftResult = preorder(root.getLeft());
        result.addAll(leftResult);

        List<T> rightResult = preorder(root.getRight());
        result.addAll(rightResult);

        return result;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> result = new ArrayList<T>();

        if (root == null) {
            return result;
        }

        List<T> leftResult = inorder(root.getLeft());
        result.addAll(leftResult);

        result.add(root.getData());

        List<T> rightResult = inorder(root.getRight());
        result.addAll(rightResult);

        return result;
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> result = new ArrayList<T>();

        if (root == null) {
            return result;
        }

        List<T> leftResult = postorder(root.getLeft());
        result.addAll(leftResult);

        List<T> rightResult = postorder(root.getRight());
        result.addAll(rightResult);

        result.add(root.getData());

        return result;
    }

    // public static void main(String[] args) {
    // TreeNode<Integer> one = new TreeNode<Integer>(1);
    // TreeNode<Integer> two = new TreeNode<Integer>(2);
    // TreeNode<Integer> three = new TreeNode<Integer>(3);
    // TreeNode<Integer> four = new TreeNode<Integer>(4);
    // TreeNode<Integer> five = new TreeNode<Integer>(5);
    // TreeNode<Integer> six = new TreeNode<Integer>(6);
    // TreeNode<Integer> seven = new TreeNode<Integer>(7);
    // TreeNode<Integer> eight = new TreeNode<Integer>(8);
    // TreeNode<Integer> nine = new TreeNode<Integer>(9);
    // TreeNode<Integer> ten = new TreeNode<Integer>(10);

    // six.setLeft(four);
    // six.setRight(seven);

    // four.setLeft(two);
    // four.setRight(five);

    // two.setLeft(one);
    // two.setRight(three);

    // seven.setRight(nine);

    // nine.setLeft(eight);
    // nine.setRight(ten);

    // Traversals<Integer> t = new Traversals<>();
    // System.out.println(t.preorder(six));
    // System.out.println(t.inorder(six));
    // System.out.println(t.postorder(six));
    // }
}