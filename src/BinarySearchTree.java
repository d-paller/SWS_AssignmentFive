
import java.io.*;
import java.util.*;

/**
 *
 * @author David
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;

    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     *
     * @param objects - an array of objects to be added
     * requires - an array of objects type E
     * ensures - the objects will be added to the tree 
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     *
     * @param e the query
     * @return false if not found or true if found
     * requires - a search key of type E
     * ensures - false will be returned if the key isn't in the tree and true if 
     * it is
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Returns true if the element is in the tree
     *
     * @param e the query
     * @param counter an array with one element to keep track of comparisons made
     * @return false if not found or true if found
     * requires - a search key of type E
     * ensures - false will be returned if the key isn't in the tree and true if 
     * it is
     */
    public boolean search(E e, int[] counter) {
        TreeNode<E> current = root; // Start from the root
        counter[0] = 0;
        int i = 0;
        while (current != null) {
            i++;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else { // element matches current.element
                counter[0] = i;
                return true; // Element is found
            }

        }
        counter[0] = i;
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     *
     * @param e the element to be inserted 
     * @return false if it is a duplicate and therefore not inserted and true if it is
     * requires - an element of type E to be inserted into the tree
     * ensures - true will be returned if the element is inserted and false if it's
     * not, which would mean that it is a duplicate
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     *
     * @param e the data for the node being created
     * @return the new tree node
     * requires - an element of type E
     * ensures - a new tree node will be created
     */
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     * requires - nothing
     * ensures - the in inorder list will be printed
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     *
     * @param root the root of the tree
     * requires - a root node  
     * ensures - the inorder traversal of the tree will be printed.
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     *
     * @param root the root node of the tree
     * @param list the list containing the inorder traversal
     * @return the list
     * requires - a root node, a list to be loaded with the inorder traversal 
     * ensures - the list with the inorder traversal will be returned 
     */
    protected ArrayList<E> inorder(TreeNode<E> root, ArrayList<E> list) {
        if (root == null) {
            return list;
        }

        inorder(root.left);
        list.add(root.element);
        inorder(root.right);

        return list;
    }

    /**
     * Postorder traversal from the root
     * requires - nothing
     * ensures - the postorder traversal will be outputed 
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     *
     * @param root a root node
     * requires - a root node 
     * ensures - the postorder traversal will be printed
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     * requires - nothing  
     * ensures - the preorder will be printed
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     *
     * @param root a root node
     * requires - a root node
     * ensures - that the preorder traversal will be printed 
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     *
     * @param root a root node
     * @param list a list to be loaded with the preorde traversal 
     * @return the list
     * requires - a root node, an ArrayList
     * ensures - the list will be loaded with the preorder traversal
     * 
     */
    protected ArrayList<E> preorder(TreeNode<E> root, ArrayList<E> list) {
        if (root == null) {
            return list;
        }
        list.add(root.element);
        preorder(root.left, list);
        preorder(root.right, list);
        return list;
    }

    /**
     * Inner class tree node
     *
     * @param <E> an object that is comparable
     * 
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         *
         * @param e
         */
        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     *
     * @return the size of the tree
     * requires - nothing
     * ensures - the size will be returned
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     *
     * @return the root node
     * requires - nothing
     * ensures - the root node will be returned
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns a path from the root leading to the specified element
     *
     * @param e key
     * @return the list containing the path to the key
     * requires - a key
     * ensures - a list with the path will be returned
     */
    public ArrayList<E> path(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        java.util.ArrayList<E> emptyList = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        if (root != null) {

            while (current != null) {
                if (e.compareTo(current.element) < 0) {

                    list.add(current.element);
                    current = current.left;

                }//if
                else if (e.compareTo(current.element) > 0) {

                    list.add(current.element);
                    current = current.right;
                }//else if
                else // element matches current.element
                {
                    return list; // Element is found
                }
            }//while
            return emptyList;
        }//if
        else {
            return emptyList;// Return an array of elements
        }
    }//path

    /* Returns the number of leaf nodes in this tree*/
    /**
     *
     * @return the number of leaves
     * requires -  
     * ensures - the number of leaves in the tree will be returned
     */
    public int getNumberOfLeaves() {
        int[] leaves = new int[1];
        TreeNode<E> current = root;

        leaves(current, leaves);

        return leaves[0];

    }

    /**
     *
     * @param root a root node
     * @param leaves an array
     * requires - a root node and an array where the amount of leaves is stored 
     * ensures - the number of leaves in the tree starting at the given root 
     * will be stored in the array
     */
    public void leaves(TreeNode<E> root, int[] leaves) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            leaves[0]++;
        }

        leaves(root.right, leaves);
        leaves(root.left, leaves);
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree */
    /**
     *
     * @param e the starting node
     * @return a list with the left sub tree
     * requires - an element of type e
     * ensures - the left sub tree from e will be stored in an arrayList and returned
     */
    public ArrayList<E> leftSubTree(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        java.util.ArrayList<E> emptyList = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        if (root != null) {

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                }//if
                else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                }//else if
                else // element matches current.element
                {
                    return preorder(current.left, list); // Element is found
                }

            }//while

            return emptyList;
        }//if
        else {
            return emptyList;// Return an array of elements
        }
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree */
    /**
     *
     * @param e the starting node
     * @return a list with the right sub tree
     * requires - an element of type e
     * ensures - the right sub tree from e will be stored in an arrayList and returned
     */
     
    public ArrayList<E> rightSubTree(E e) {

        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        java.util.ArrayList<E> emptyList = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        if (root != null) {

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                }//if
                else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                }//else if
                else // element matches current.element
                {
                    return preorder(current.right, list); // Element is found
                }

            }//while

            return emptyList;
        }//if
        else {
            return emptyList;// Return an array of elements
        }
    }

    /**
     *Returns the inorder predecessor of the specified element
     * @param e the element that is being searched for
     * @return the indorder predecessor of e
     * requires - the element that needs the inorder predecessor
     * ensures - the inorder predecessor will be returned 
     */
    public E inorderPredecessor(E e) {

        TreeNode<E> current = root; // Start from the root
        TreeNode<E> temp = null;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                temp = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                temp = current;
                current = current.right;
            } else // element matches current.element
            {
                return temp.element;
            }
        }
        return null;
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     *
     * @param e the element to be deleted 
     * @return the deleted element
     * requires - an element of type e to be deleted 
     * ensures - that e will be removed from the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {

            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
      // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     *
     * @return the inorder iterator
     * requires - 
     * ensures - that the inorder iterator will be returned 
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     *
     * @return 
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {

        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         * requires - nothing
         * ensures - the inorder traversal will be added to a list
         * 
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         * requires - a root node
         * ensures - the inorder traversal will be added to a list
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         * requires - nothing
         * ensures - true if there is a next element in the tree, false if there
         * is not
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         * requires - nothing
         * ensures - the cursor will be moved to the next element in the list
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         * requires - nothing
         * ensures - removes the current element from the tree
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     * requires - nothing
     * ensures - the tree will be cleared
     */
    public void clear() {
        root = null;
        size = 0;
    }

}
