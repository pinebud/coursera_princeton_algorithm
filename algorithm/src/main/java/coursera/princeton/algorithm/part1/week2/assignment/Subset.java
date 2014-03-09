public class Subset {
	public static void main(String[] args) {
		int subsetSize = Integer.valueOf(args[0]);

		RandomizedQueue<String> rqueue = new RandomizedQueue<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (item.equalsIgnoreCase("exit"))
				break;
			rqueue.enqueue(item);
		}

		if (!rqueue.isEmpty()) {
			int i = 1;
			for (String item : rqueue) {
				if (i++ > subsetSize)
					break;
				StdOut.println(item);
			}
		}
	}
}

