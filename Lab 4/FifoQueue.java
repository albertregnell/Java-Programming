package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> node = new QueueNode<E>(e);
		if(last == null) {
			last = node;
			last.next = last;
			size++;
			return true;
		}
		node.next = last.next;
		last.next = node;
		last = node;
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last == null) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(last == null) {
			return null;
		}
		E temp = peek();
		
		if(size == 1) {
			last = null;
		}else {
			last.next = last.next.next;
		}
		size--;
		return temp;
	}
	public void append(FifoQueue<E> q) {
		if(this == q) {
			throw new IllegalArgumentException();
		}else if(last == null) {
			last = q.last;
			size += q.size;
			q.last = null;
			q.size = 0;
		}else if(q.last == null) {
			return;
		}else{
		QueueNode<E> temp = last.next;
		last.next = q.last.next;
		q.last.next = temp;
		last = q.last;
		q.last = null;
		size += q.size;
		q.size = 0;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		 private QueueNode<E> pos;
		 
		 private QueueIterator() {
			 pos = null;
		 }
         public boolean hasNext() {
        	 if(pos == last) {
        		 return false;
        	 }
        	 return true;
         }
         public E next() {
        	 if(hasNext()) {
        		 if(pos == null) {
        			 pos = last.next;
        			 
        		 }else {
        			 pos = pos.next; 
        		 }
        		 return pos.element; 
        	 }else {
        		 throw new NoSuchElementException();
        	 }
         }
         
	}
	public static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
