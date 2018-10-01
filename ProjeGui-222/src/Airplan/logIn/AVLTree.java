package Airplan.logIn;

/**
 * Created by serhan on 31.05.2018.
 */
import java.util.LinkedList;
import java.util.Queue;

/**
 * Self-balancing binary search tree using the algorithm defined
 * by Adelson-Velskii and Landis.
 * @author Koffman and Wolfgang
 */
public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    // Insert nested class AVLNode<E> here.
    /*<listing chapter="9" number="2">*/
    /** Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends Node<E> {

        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** balance is right subtree height - left subtree height */
        private int balance;

        // Methods
        /**
         * Construct a node with the given item as the data field.
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }
    /*</listing>*/
    // Data Fields
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;
    private boolean decrease;

    public AVLTree(){}


    public AVLTree(BinaryTree tree){
        if (!isAVL(tree.root)){
            throw new UnsupportedOperationException("This is not AVL tree");
        }
        else {
            Queue<E> temp= new LinkedList<>();
            getLevelOrder(temp, tree.root);     // get tree as level order
            while (!temp.isEmpty()){
                add(temp.poll());
            }

        }

    }
//--------------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @param localRoot
     * @return
     */
    public boolean isAVL(Node<E> localRoot){
        if (localRoot == null)
            return true;

        if ( (localRoot.left != null &&  localRoot.data.compareTo(localRoot.left.data) <= 0  ) ||    // root <= root.left
                (localRoot.right != null && localRoot.data.compareTo(localRoot.right.data) >= 0 )     ) // root >= root.right
        {
            return false;
        }

        int left  = height(localRoot.left);
        int right = height(localRoot.right);

        if ( Math.abs(left-right) <= 1  && isAVL(localRoot.left) && isAVL(localRoot.right)){
            return true;
        }

        return false;

    }

    /**
     *
     * @param node
     * @return
     */
    private int height(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            int lHeight = height(node.left);  // left sub tree height
            int rHeight = height(node.right); // right sub tree height

            if (lHeight < rHeight)
                return (rHeight + 1);
            else
                return (lHeight + 1);
        }
    }

    /**
     *
     * @param items
     * @param root
     */
    private void getLevelOrder(Queue<E> items, Node<E> root)
    {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {

            Node<E> tempNode = queue.poll();
            items.add(tempNode.data);

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }


            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }


//--------------------------------------------------------------------------------------------------------------------------------

    // Methods
    /**
     * add starter method.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     * @post addReturn is set true if the item is inserted,
     *       false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root of the subtree with the item
     *         inserted
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot, false);
                }
            }
            return localRoot; // Rebalance not needed.
        }
        else { // item > data
            localRoot.right = add((AVLNode<E>) localRoot.right, item);

            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase=false;
                    return rebalanceRight(localRoot, false);
                }
            }
            return localRoot;
        }
    }

    /**
     *
     * @param item
     * @return
     */
    public E delete(E item) {
        decrease = false;
        root = delete( (AVLNode < E > ) root, item);
        return deleteReturn;
    }

    /**
     *
     * @param localRoot
     * @param item
     * @return
     */
    private AVLNode<E> delete(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }

        if (item.compareTo(localRoot.data) == 0) {
            deleteReturn = localRoot.data;
            return BSDelete(localRoot);
        }
        else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = delete((AVLNode<E>) localRoot.left, item);

            if (decrease) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    return rebalanceRight(localRoot, true);
                }
            }
            return localRoot; // Rebalance not needed.
        }
        else { // item > data
            localRoot.right = delete((AVLNode<E>) localRoot.right, item);

            if (decrease) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    return rebalanceLeft(localRoot, true);
                }
            }
            return localRoot;
        }
    }

    /**
     *
     * @param localRoot
     * @return
     */
    private AVLNode<E> BSDelete(AVLNode<E> localRoot)
    {
        if (localRoot == null)  return localRoot;

        if (localRoot.left == null){ // only right child, may be not
            decrease = true;
            return (AVLNode<E>) localRoot.right;
        }
        else if (localRoot.right == null) {  // only left child, may be not
            decrease = true;
            return (AVLNode<E>) localRoot.left;
        }
        else{
            if (localRoot.right.left == null) {
                localRoot.data = localRoot.right.data;
                localRoot.right = localRoot.right.right; // wanted node deleted
                decrementBalance(localRoot); // because of remove from right tree
                return localRoot;
            }
            else {
                localRoot.data = minValue( (AVLNode < E > ) localRoot.right); // get smallest node from localRoot.right then replace new value
                if ( ( (AVLNode < E > ) localRoot.right).balance > AVLNode.RIGHT_HEAVY) {
                    localRoot.right = rebalanceRight( (AVLNode < E > ) localRoot.right, false);
                }
                if (decrease) {
                    decrementBalance(localRoot);
                }
                return localRoot;
            }
        }
    }

    /**
     *
     * @param localRoot
     * @return
     */
    private E minValue(AVLNode<E> localRoot)
    {
        if (localRoot.left.left == null){   // the smallest element
            E returnValue = localRoot.left.data;
            localRoot.left = localRoot.left.right;  // returned node, deleted
            incrementBalance(localRoot);
            return returnValue;
        }
        else {
            E returnValue = minValue( (AVLNode < E > ) localRoot.left);     // until reach the smallest elemnt (the most left)
            if ( ( (AVLNode < E > ) localRoot.left).balance > AVLNode.RIGHT_HEAVY) {
                localRoot.left = rebalanceRight( (AVLNode < E > ) localRoot.left, false);
            }
            if (decrease) {
                incrementBalance(localRoot);
            }
            return returnValue;
        }
    }

/*
    decrement -1  --> -2 increase true
               0  --> -1 increase true
               1  -->  0 increse  false

    increment -1  --> 0 increase false
               0  --> 1 increase true
               1  --> 2 increse  true

              -1
               0
               1

 */


    /*<listing chapter="9" number="3">*/
    /**
     * Method to rebalance left.
     * @pre localRoot is the root of an AVL subtree that is
     *      critically left-heavy.
     * @post Balance is restored.
     * @param localRoot Root of the AVL subtree
     *        that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot, boolean isDelete) {
        // Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        if (isDelete == false){
            // See whether left-right heavy.
            if (leftChild.balance > AVLNode.BALANCED) {
                // Obtain reference to left-right child.
                AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
                // Adjust the balances to be their new values after
                // the rotations are performed.
                if (leftRightChild.balance < AVLNode.BALANCED) {
                    leftChild.balance = AVLNode.BALANCED; //AVLNode.LEFT_HEAVY;
                    leftRightChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.RIGHT_HEAVY; //AVLNode.BALANCED;
                } else if (leftRightChild.balance > AVLNode.BALANCED) {
                    leftChild.balance = AVLNode.LEFT_HEAVY; //AVLNode.BALANCED;
                    leftRightChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;   //AVLNode.RIGHT_HEAVY;
                } else {
                    leftChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;
                }
                // Perform left rotation.
                localRoot.left = rotateLeft(leftChild);
            } else { //Left-Left case
                // In this case the leftChild (the new root)
                // and the root (new right child) will both be balanced
                // after the rotation.
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
        }else {
            if (leftChild.balance > AVLNode.BALANCED) {     // left-right

                AVLNode < E > leftRightChild = (AVLNode < E > ) leftChild.right;

                if (leftRightChild.balance < AVLNode.BALANCED) { // left - right - left
                    leftChild.balance = AVLNode.LEFT_HEAVY;
                    leftRightChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;
                }
                else if (leftRightChild.balance > AVLNode.BALANCED) {  // left - right - right
                    leftChild.balance = AVLNode.BALANCED;
                    leftRightChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.RIGHT_HEAVY;
                }
                else {  // left- right
                    leftChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;
                }

                increase = false;
                decrease = true;

                localRoot.left = rotateLeft(leftChild);
            }
            else if (leftChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
                increase = false;
                decrease = true;
            }
            else {
                leftChild.balance = AVLNode.RIGHT_HEAVY;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    /**
     *
     * @param localRoot
     * @return
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot, boolean isDelete) {

        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;

        if (isDelete == false){ // for add

            if (rightChild.balance < AVLNode.BALANCED) {  // Right-Left case

                AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;

                if (rightLeftChild.balance > AVLNode.BALANCED) { // Right-Left-right
                    rightChild.balance = AVLNode.BALANCED;
                    rightLeftChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.LEFT_HEAVY;
                }
                else if (rightLeftChild.balance < AVLNode.BALANCED) { // Right-Left -left
                    rightChild.balance = AVLNode.RIGHT_HEAVY;
                    rightLeftChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;
                }
                else {  // Right-Left
                    rightChild.balance = AVLNode.BALANCED;
                    rightLeftChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;
                }

                localRoot.right = rotateRight(rightChild); // to make Right-Right case
            } else { //Right-Right case
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
        }else { // for delete
            if (rightChild.balance < AVLNode.BALANCED) {  // right-left

                AVLNode < E > rightLeftChild = (AVLNode < E > ) rightChild.left;

                if (rightLeftChild.balance > AVLNode.BALANCED) { // right-left- right
                    rightChild.balance = AVLNode.RIGHT_HEAVY;
                    rightLeftChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;
                }
                else if (rightLeftChild.balance < AVLNode.BALANCED) {   // right-left-left
                    rightChild.balance = AVLNode.BALANCED;
                    rightLeftChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.LEFT_HEAVY;
                }
                else {  //  right-left
                    rightChild.balance = AVLNode.BALANCED;
                    localRoot.balance = AVLNode.BALANCED;
                }
                increase = false;
                decrease = true;

                localRoot.right = rotateRight(rightChild);

            }
            else if (rightChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
                increase = false;
                decrease = true;
            }
            else {
                rightChild.balance = AVLNode.LEFT_HEAVY;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
        }
        return (AVLNode<E>) rotateLeft(localRoot); // to balance
    }



    /*</listing>*/

// Insert solution to programming exercise 1, section 2, chapter 9 here

    /**
     * Method to decrement the balance field and to reset the value of
     * increase.
     * @pre The balance field was correct prior to an insertion [or
     *      removal,] and an item is either been added to the left[
     *      or removed from the right].
     * @post The balance is decremented and the increase flags is set
     *       to false if the overall height of this subtree has not
     *       changed.
     * @param node The AVL node whose balance is to be incremented
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased but decreased.
            increase = false;
            decrease = true;
        }else {
            decrease = false;
        }
    }

    /**
     *
     * @param node
     */
    private void incrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance++;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased but decreased.
            increase = false;
            decrease = true;
        }else {
            decrease = false;
        }
    }


}
