mport java.util.Iterator;
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
		Node cur = first.next;

		@Override
		public boolean hasNext() {
			return (last != cur);
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
		first = new Node(null, null, null);
		last = new Node(null, first, null);
		first.next = last;
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
		Node n = first.next;
		first.next = new Node(item, first, n);
		n.pre = first.next;
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
		Node n = last.pre;
		last.pre = new Node(item, n, last);
		n.next = last.pre;
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
		Node n = first.next;
		
		// Copy
		Item ret = n.item;
		first.next = n.next;
		n.next.pre = first;

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
		Node n = last.pre;

		// Copy
		Item ret = n.item;
		last.pre = n.pre;
		n.pre.next = last;
		
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
