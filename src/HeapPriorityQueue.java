import java.util.Arrays;
import java.util.NoSuchElementException;

// Implements a priority queue of comparable objects using a
// min-heap represented as an array.
@SuppressWarnings("unused")
public class HeapPriorityQueue<E extends Comparable<E>>
{
    private E[] elementData;
    private int size;

    // Constructs an empty queue.
    @SuppressWarnings("unchecked")
    public HeapPriorityQueue()
    {
        elementData = (E[]) new Comparable[10];
        size = 0;
    }

    // Adds the given element to this queue.
    // by: Victor
    // time complexity: O(log N)
    public void add(E value)
    {
        if (size == elementData.length - 1)
        {
            elementData = Arrays.copyOf(elementData, 2 * elementData.length);
        }
        
        elementData[size + 1] = value;
        int index = size + 1;
        boolean found = false;
        // bubble up
        while (!found && hasParent(index))
        {
            int parent = parent(index);
            if (elementData[index].compareTo(elementData[parent]) < 0)
            {
                swap(elementData, index, parent(index));
                index = parent(index);
            }
            else
            {
                found = true;
            }
        }
        
        size++;
    }

    // Returns true if there are no elements in this queue.
    // by: Jacob
    // time complexity: O(1)
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Returns the minimum value in the queue without modifying the queue.
    // If the queue is empty, throws a NoSuchElementException.
    // by: Jacob
    // time complexity: O(1)
    public E peek()
    {
        if (isEmpty()) throw new NoSuchElementException();
        return elementData[1];
    }

    // Removes and returns the minimum value in the queue.
    // If the queue is empty, throws a NoSuchElementException.
    // by: Clyde
    // time complexity: O(log N)
    public E remove()
    {
        if(size == 0) throw new NoSuchElementException();
        E result = elementData[1];
        elementData[1] = elementData[size];
        elementData[size] = null;
        size--;
        int index = 1; 
        boolean found = false;
        while(!found && hasLeftChild(index)) {//keeps bubbling down until in order/already at end of heap
            int left = leftChild(index);
            int right = rightChild(index);
            int child = left;
            if(right <= size && elementData[right].compareTo(elementData[left]) < 0){//selects smaller child to compare to node
                child = right;          
            }      
            if(elementData[index].compareTo(elementData[child]) > 0) { //bubbles down if not in order and changes index to check next children
                swap(elementData, index, child);
                index = child;
            } else {
                found = true; //proper location found
            }
        }       
        return result;       
    }

    // Returns the number of elements in the queue.
    // by: Clyde
    // time complexity: O(1)
    public int size()
    {        
        return size;
    }

    // Returns a string representation of this queue, such as "[10, 20, 30]";
    // The elements are not guaranteed to be listed in sorted order.
    public String toString()
    {
        StringBuilder result = new StringBuilder("[");
        if (!isEmpty())
        {
            result.append(elementData[1]);
            for (int i = 2; i <= size; i++)
            {
                result.append(", ").append(elementData[i]);
            }
        }
        return result + "]";
    }

    // helpers for navigating indexes up/down the tree
    // by: Victor
    // time complexity: O(1)
    private int parent(int index)
    {
        return index / 2;
    }

    // returns index of left child of given index
    // by: Clyde
    // time complexity: O(1)
    private int leftChild(int index)
    {
        return 2 * index;
    }

    // returns index of right child of given index
    // by: Clyde
    // time complexity: O(1)
    private int rightChild(int index)
    {
        return (2 * index) + 1;
    }

    // returns true if the node at the given index has a parent (is not the
    // root)
    // by: Jacob
    // time complexity: O(1)
    private boolean hasParent(int index)
    {
        return index > 1;
    }

    // returns true if the node at the given index has a non-empty left child
    // by: Victor
    // time complexity: O(1)
    private boolean hasLeftChild(int index)
    {
        return leftChild(index) <= size;
    }

    // returns true if the node at the given index has a non-empty right child
    // by: Victor
    // time complexity: O(1)
    private boolean hasRightChild(int index)
    {
        return rightChild(index) <= size;
    }

    // switches the values at the two given indexes of the given array
    // by: Clyde
    // time complexity: O(1)
    private void swap(E[] a, int index1, int index2)
    {
        // TO DO
        E val = a[index1];
        a[index1] = a[index2];
        a[index2] = val;
        
    }
}
