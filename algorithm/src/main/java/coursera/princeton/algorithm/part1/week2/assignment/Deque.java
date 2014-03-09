import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author quantarick@gmail.com
 * 
 * @param <Item>
 * 
 *            A double-ended queue or deque (pronounced "deck") is a
 *            generalization of a stack and a queue that supports inserting and
 *            removing items from either the front or the back of the data
 *            structure.
 */
public class Deque<Item> implements Iterable<Item> {
	private int N; // size of Deque
	private Node first, last; //

	private class Node {
		private Item item;
		private Node pre, next;

		public Node(Item item, Node pre, Node next) {
			this.item = item;
			this.pre = pre;
			this.next = next;
		}
	}

	private class DequeIterator implements Iterator<Item> {
		Node cur = first;

		@Override
		public boolean hasNext() {
			return (null != cur);
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = cur.item;
			cur = cur.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * construct an empty randomized queue.
	 */
	public Deque() {
		N = 0;
		first = last = null;
	}

	/**
	 * is the queue empty?
	 * 
	 * @return empty
	 */
	public boolean isEmpty() {
		return (0 == N);
	}

	/**
	 * return the number of items on the deque.
	 * 
	 * @return current item # of queue
	 */
	public int size() {
		return N;
	}

	/**
	 * insert the item at the front
	 * 
	 * @param item
	 * @throws NullPointerException
	 *             if add queue with a null value.
	 */
	public void addFirst(Item item) throws NullPointerException {
		if (null == item)
			throw new NullPointerException();
		if (0 == N) {
			first = new Node(item, null, null);
			last = first;
		} else {
			first.pre = new Node(item, null, first);
			first = first.pre;
		}
		N++;
	}

	/**
	 * insert the item at the end.
	 * 
	 * @param item
	 * @throws NullPointerException
	 *             if add queue with a null value.
	 */
	public void addLast(Item item) throws NullPointerException {
		if (null == item)
			throw new NullPointerException();
		if (0 == N) {
			last = new Node(item, null, null);
			first = last;
		} else {
			last.next = new Node(item, last, null);
			last = last.next;
		}
		N++;
	}

	/**
	 * delete and return the item at the front
	 * 
	 * @return first item
	 * @thorws NoSuchElementException if remove an item from an empty queue.
	 */
	public Item removeFirst() {
		if (0 == N)
			throw new NoSuchElementException();
		Node oldFirst = first;
		// Copy
		Item ret = oldFirst.item;
		first = oldFirst.next;

		// Deref to avoid loitering
		if (first != null)
			first.pre = null;

		N--;
		return ret;
	}

	/**
	 * delete and return the item at the end
	 * 
	 * @return last item
	 * @thorws NoSuchElementException if remove an item from an empty queue.
	 */
	public Item removeLast() {
		if (0 == N)
			throw new NoSuchElementException();
		Node oldLast = last;

		Item ret = oldLast.item;
		last = oldLast.pre;

		// Deref to avoid loitering
		if (last != null)
			last.next = null;

		N--;
		return ret;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	/**
	 * Unit test.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// constructor test.
		Deque<String> deque = new Deque<String>();

		// addFirst/addLast/removeFirst/removeLast tests.
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (item.equalsIgnoreCase("exit"))
				break;
			else if (item.startsWith("++"))
				deque.addFirst(item);
			else if (item.startsWith("+"))
				deque.addLast(item);
			else if (item.equals("--"))
				deque.removeFirst();
			else if (item.equals("-"))
				deque.removeLast();
		}

		// size() test.
		StdOut.println("Size of Deque is " + deque.size());

		// DequeIterator test.
		if (!deque.isEmpty()) {
			for (String item : deque) {
				StdOut.println(item);
			}
		}
	}
}
