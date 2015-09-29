package week2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Item[] deque;
	private int N = 0;
	private int tail;

	// construct an empty deque
	public Deque() {
		this.deque = (Item[]) new Object[2];
		this.tail = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return N == 0;
	}

	// return the number of items on the deque
	public int size() {
		return N;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		if (tail == deque.length) {
			resize(2 * deque.length);
		}

		for (int j = N - 1; j >= 0; j--) {
			deque[j + 1] = deque[j];
		}

		deque[0] = item;
		N++;
		tail++;

	}

	private void resize(int i) {
		Item[] copy = (Item[]) new Object[i];
		for (int j = 0; j < N; j++) {
			copy[j] = deque[j];
		}
		deque = copy;
	}

	// add the item to the end
	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		if (tail == deque.length) {
			resize(2 * deque.length);
		}
		deque[tail] = item;
		N++;
		tail++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		Item item = deque[0];
		for (int j = 0; j < N - 1; j++) {
			deque[j] = deque[j + 1];
		}
		N--;
		deque[--tail] = null;

		if (N > 0 && N == deque.length / 4) {
			resize(deque.length / 2);
		}

		return item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		Item item = deque[--tail];
		N--;
		deque[tail] = null;
		if (N > 0 && N == deque.length / 4) {
			resize(deque.length / 2);
		}
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		Iterator<Item> it = new Iterator<Item>() {
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < deque.length && deque[currentIndex] != null;
			}

			@Override
			public Item next() {
				if (N==currentIndex){
					throw new java.util.NoSuchElementException();
				}
				return deque[currentIndex++];
			}

			public void remove() {
				throw new java.lang.UnsupportedOperationException();
			}

		};
		return it;

	}

	public static void main(String[] args) {
		

	}

}
