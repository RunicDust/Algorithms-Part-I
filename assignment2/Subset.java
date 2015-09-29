package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	public static void main(String[] args) {

		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		String[] items = StdIn.readLine().split(" ");

		for (String item : items) {
			rq.enqueue(item);
		}

		for (int i = 0; i < k; i++) {
			StdOut.println(rq.dequeue());
		}

	}
}