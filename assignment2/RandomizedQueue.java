package week2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] q; // queue of items
	private int N; // number of elements
	private int tail;

	// construct an empty randomized queue
	public RandomizedQueue() {
		q = (Item[]) new Object[2];
	}

	// is the queue empty?
	public boolean isEmpty() {
		return N == 0;
	}

	// return the number of items on the queue
	public int size() {
		return N;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		if (tail == q.length) {
			resize(2 * q.length);
		}
		q[tail] = item;
		N++;
		tail++;
	}

	private void resize(int i) {
		Item[] copy = (Item[]) new Object[i];
		for (int j = 0; j < q.length; j++) {
			if (q[j] != null) {
				copy[j] = q[j];
			}
		}
		q = copy;
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int randIndex = StdRandom.uniform(tail);
		Item item = q[randIndex];
		q[randIndex] = q[tail - 1];
		q[tail - 1] = null;
		tail--;
		N--;
		if (N > 0 && N == q.length / 4) {
			resize(q.length / 2);
		}

		return item;

	}

	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Item item = q[StdRandom.uniform(tail)];
		return item;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {	return new RandomizedQueueIterator();}
	
	 private class RandomizedQueueIterator implements Iterator<Item> {

         private Item[] queueCopy;
         private int currentIndex = 0;
         
         private RandomizedQueueIterator() {
              queueCopy = (Item[]) new Object[N];
              for (int i = 0; i < N; i++) {
            	  queueCopy[i]=q[i];
			}   

              StdRandom.shuffle(queueCopy);
         }
         
         public boolean hasNext() {
        	 
        	 return currentIndex < queueCopy.length && queueCopy[currentIndex] != null;
         }

         public Item next() {
				if (N==currentIndex){
					throw new java.util.NoSuchElementException();
				}
				return queueCopy[currentIndex++];
         }

         public void remove() {
                 throw new java.lang.UnsupportedOperationException();
         }
 }




	// unit testing
	public static void main(String[] args) {
		// RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		// for (int i = 0; i < 5; i++) {
		// rq.enqueue(i);
		// }
		// System.out.println("N = " + rq.N);
		// System.out.println(Arrays.toString(rq.q));
		// rq.dequeue();
		// rq.dequeue();
		// rq.dequeue();
		// rq.dequeue();
		// rq.dequeue();
		// System.out.println("N = " + rq.N);
		// System.out.println(Arrays.toString(rq.q));
		// rq.sample();
		
	}
}
