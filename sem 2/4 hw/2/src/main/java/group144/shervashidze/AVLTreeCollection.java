package group144.shervashidze;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class realizing Collection as AVL tree.
 *
 * @param <T> Type of elements. Should be comparable
 */
public class AVLTreeCollection<T extends Comparable<T>> implements Collection<T> {
    private Guardian head;
    private int size;

    /**
     * Initialization method.
     * sets head as new guardian.
     * sets size as 0.
     */
    public AVLTreeCollection() {
        head = new Guardian();
        size = 0;
    }

    /**
     * error print as (value (value null null) (value null null)) and etc.
     * tells head guardian to print his structure.
     *
     * @return String error print.
     */
    public String print() {
        return  head.toString();
    }

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the number of elements in this collection.
     *
     * @return size - amount of elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Contains.
     *
     * @return true if element in this collection, false otherwise.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return head.find((T) o);
    }

    /**
     * Iterator.
     * elements in increasing order(dfs of AVL tree)
     *
     * @return AVLTreeIterator(T type of elements in it).
     */
    @Override
    public Iterator<T> iterator() {
        return new AVLTreeIterator();
    }

    /**
     * AVLTreeIterator
     * put all elements in ArrayList<T>/</T> (dfs).
     */
    private class AVLTreeIterator implements Iterator<T> {
        private ArrayList<T> elements;

        AVLTreeIterator() {
            elements = new ArrayList<>();
            head.takeAll(elements);
        }

        /**
         * hasNext.
         *
         * @return true if there are elements in Array List, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return !elements.isEmpty();
        }

        /**
         * next.
         * removes elements from Array List.
         *
         * @return current element, null if there is no elements left.
         */
        @Override
        public T next() {
            if (isEmpty()) {
                return null;
            }

            return elements.remove(0);
        }
    }

    /**
     * toArray.
     *
     * @return elements of collection as array of Objects in increasing order.
     */
    @Override
    public Object[] toArray() {
        ArrayList<T> result = new ArrayList<>();
        head.takeAll(result);
        return result.toArray();
    }

    /**
     * toArray.
     *
     * @param <T1> the runtime type of the array to contain the collection.
     * @param a the array into which the elements of this collection are supposed to be
     *          stored, if array is not big enough a new array of the same runtime type
     *          will be created.
     * @return the array containing all of the elements in this collection in inc order.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        ArrayList<T1> result = new ArrayList<>();
        ArrayList<T> buff = new ArrayList<>();
        head.takeAll(buff);
        for (T value : buff)
            result.add((T1) value);
        return result.toArray(a);
    }

    /**
     * add.
     * More than 1 element can be stored.
     *
     * @param t(type T) to add in collection.
     * @return true always, 'cause my realization allow to store more than 1 element in collection.
     */
    @Override
    public boolean add(T t) {
        size++;
        head.add(t);
        return true;
    }

    /**
     * remove.
     *
     * return true, if there was element in collection, false otherwise.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        if (head.remove((T) o)) {
            size--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * containsAll.
     * contain for each element of c.
     *
     * @param c - collection to get elements.
     * @return true if all elements of c stored in AVLTreeCollection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean answer = true;
        for (Object element : c) {
            answer = answer && contains(element);
        }

        return answer;
    }

    /**
     * addAll.
     * add for each element of c.
     *
     * @param c - collection to get elements.
     * @return true always.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        boolean answer = true;
        for (Object element : c) {
            answer = answer && add((T) element);
        }

        return answer;
    }

    /**
     * removeAll.
     * contain for each element of c.
     *
     * @param c - collection to get elements.
     * @return true if all elements of c was stored in AVLTreeCollection.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean answer = true;
        for (Object element : c) {
            answer = answer && remove(element);
        }

        return answer;
    }

    /**
     * retainAll.
     * remove for each element of c.
     *
     * @param c - collection to get elements.
     * @return true if AVLTreeCollection changed, false otherwise.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean answer = false;
        for (Object element : c) {
            if (contains(element)) {
                remove(element);
                answer = true;
            }
        }

        return answer;
    }

    /**
     * clear.
     * clears collection.
     * sets head.client as null.
     * sets size as 0.
     */
    @Override
    public void clear() {
        head.client = null;
        size = 0;
    }

    /**
     * Guardian
     * Guardian for Node of avl tree.
     * Created to realize avl tree methods.
     * Realizes add, remove, boolean find(contains), guardian findMax(max node), rotations, balance,
     * toString(return error print), takeAll(ArrayList<T></T> to store elements).
     *
     * Add, remove, findMax, rotations, balance is standard for AVLTree.
     */
    private class Guardian {
        private TreeNode client;

        void add(T newValue) {
            if (client == null) {
                client = new TreeNode(newValue);
                return;
            }
            if (client.value.equals(newValue)) {
                client.amount++;
                return;
            }

            if (client.value.compareTo(newValue) > 0) {
                client.left.add(newValue);
            } else {
                client.right.add(newValue);
            }

            balance();
        }

        boolean remove(T newValue) {
            if (client == null) {
                return false;
            }

            if (client.value.equals(newValue)) {
                if (client.amount > 1) {
                    client.amount--;
                    return true;
                }
                if (client.left.client == null && client.right.client == null) {
                    client = null;
                } else if (client.left.client == null) {
                    client = client.right.client;
                } else if (client.right.client == null) {
                    client = client.left.client;
                } else {
                    Guardian temp = client.left.findMax();
                    T maxValue = temp.client.value;
                    int maxAmount = temp.client.amount;
                    while (client.left.find(maxValue)) {
                        client.left.remove(maxValue);
                    }
                    client.value = maxValue;
                    client.amount = maxAmount;
                    balance();
                }

                return true;
            }

            boolean answer;
            if (client.value.compareTo(newValue) > 0){
                answer = client.left.remove(newValue);
            } else {
                answer = client.right.remove(newValue);
            }

            balance();
            return answer;
        }

        boolean find(T newValue) {
            if (client == null) {
                return false;
            }

            if (client.value.equals(newValue)) {
                return true;
            }

            if (client.value.compareTo(newValue) > 0) {
                return client.left.find(newValue);
            } else {
                return client.right.find(newValue);
            }
        }

        Guardian findMax() {
            if (client.right.client == null) {
                return this;
            } else {
                return client.right.findMax();
            }
        }

        void rotateRight() {
            Guardian temp = client.left;
            Guardian newLeft = new Guardian();
            newLeft.client = client;
            newLeft.client.left = temp.client.right;
            temp.client.right = newLeft;

            newLeft.client.fixHeight();
            temp.client.fixHeight();

            client = temp.client;
        }

        void rotateLeft() {
            Guardian temp = client.right;
            Guardian newRight = new Guardian();
            newRight.client = client;
            newRight.client.right = temp.client.left;
            temp.client.left = newRight;

            newRight.client.fixHeight();
            temp.client.fixHeight();

            client = temp.client;
        }

        void balance() {
            client.fixHeight();

            int bfactor = client.balanceFactor();
            if (bfactor == 2) {
                if (client.left.client != null && client.left.client.balanceFactor() < 0) {
                    client.left.rotateLeft();
                }
                rotateRight();
            } else if (bfactor == -2) {
                if (client.right.client != null && client.right.client.balanceFactor() > 0) {
                    client.right.rotateRight();
                }
                rotateLeft();
            }
        }

        public String toString() {

            String result = "(" + client.value.toString() + ":" + client.amount + " ";
            result += client.left.client != null ? client.left.toString() : "null";
            result += " ";
            result += client.right.client != null ? client.right.toString() : "null";
            result += ")";
            return result;
        }

        public void takeAll(ArrayList<T> list) {
            if (client == null) {
                return;
            }

            client.left.takeAll(list);
            for (int i = 0; i < client.amount; i++) {
                T v = client.value;
                list.add(v);
            }
            client.right.takeAll(list);
        }
    }

    /**
     * TreeNode
     *
     * Realizes tree Node.
     * T value stored in Node.
     * int Amount how many (value) in collection.
     * fixHeight and BalanceFactor is standard for AVLTree.
     */
    private class TreeNode {
        private T value;
        private int amount;
        private int height;
        private Guardian left;
        private Guardian right;

        TreeNode(T newValue) {
            left = new Guardian();
            right = new Guardian();
            value = newValue;
            amount = 1;
            height = 1;
        }

        private void fixHeight() {
            int leftHeight = (left.client != null ? left.client.height : 0);
            int rightHeight = (right.client != null ? right.client.height : 0);
            height = Integer.max(leftHeight, rightHeight) + 1;
        }

        private int balanceFactor() {
            return (left.client != null ? left.client.height : 0) - (right.client != null ? right.client.height : 0);
        }
    }
}
