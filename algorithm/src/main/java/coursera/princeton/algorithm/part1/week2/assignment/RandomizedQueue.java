package coursera.princeton.algorithm.part1.week2.assignment;

import edu.princeton.cs.introcs.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Deque<Item> dQueue;
	private Iterator<Item> dQueueIt;

	private class RandomizedQueueIterator implements Iterator<Item> {
		private Item[] a;
		private int[] order;
		private int idx;

		@SuppressWarnings("unchecked")
		private RandomizedQueueIterator() {
			final int size = size();
			if (size > 0) {
				a = (Item[]) new Object[size];
				int idx = 0;
				for (Item i : dQueue)
					a[idx++] = i;

				order = new int[size];
				for (idx = 0; idx < size; idx++)
					order[idx] = idx;
				StdRandom.shuffle(order);
			}

			idx = 0;
		}

		@Override
		public boolean hasNext() {
			return idx < size();
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return a[order[idx++]];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * construct an empty randomized dQueue
	 */
	public RandomizedQueue() {
		dQueue = new Deque<Item>();
	}

	/**
	 * is the queue empty?
	 * 
	 * @return is empty
	 */
	public boolean isEmpty() {
		return dQueue.isEmpty();
	}

	/**
	 * return the number of items on the queue.
	 * 
	 * @return queue size
	 */
	public int size() {
		return dQueue.size();
	}

	/**
	 * add the item
	 * 
	 * @param item
	 * @throws java.lang.NullPointerException
	 *             if the client attempts to add a null item.
	 */
	public void enqueue(Item item) {
		if (null == item)
			throw new NullPointerException();
		if (StdRandom.uniform(2) % 2 == 0) {
			dQueue.addFirst(item);
		} else {
			dQueue.addLast(item);
		}
	}

	/**
	 * delete and return a random item
	 * 
	 * @return a random item
	 * @throws java.util.NoSuchElementException
	 *             if the client attempts to sample or dequeue an item from an
	 *             empty randomized queue.
	 */
	public Item dequeue() {
		if (0 == size())
			throw new NoSuchElementException();
		return dQueue.removeFirst();
	}

	/**
	 * return (but do not delete) a random item
	 * 
	 * @return a random item.
	 * @throws java.util.NoSuchElementException
	 *             if the client attempts to sample or dequeue an item from an
	 *             empty randomized queue.
	 */
	public Item sample() {
		if (0 == size())
			throw new NoSuchElementException();
		if (null == dQueueIt || !dQueueIt.hasNext()) {
			dQueueIt = dQueue.iterator();
		}
		return dQueueIt.next();
	}

	/**
	 * return an independent iterator over items in random order
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	/**
	 * unit testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// constructor test.
		RandomizedQueue<String> rqueue = new RandomizedQueue<String>();

		// addFirst/addLast/removeFirst/removeLast tests.
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (item.equalsIgnoreCase("exit"))
				break;
			else if (item.startsWith("+"))
				rqueue.enqueue(item);
			else if (item.equals("-"))
				StdOut.println(rqueue.dequeue());
		}

		// sample test.
		StdOut.println("sample for the first time is " + rqueue.sample());
		StdOut.println("sample for the second time is " + rqueue.sample());
		
		// size() test.
		StdOut.println("Size of randomized queue is " + rqueue.size());

		// DequeIterator test.
		if (!rqueue.isEmpty()) {
			for (String item : rqueue) {
				StdOut.println(item);
			}
		}
	}
}
