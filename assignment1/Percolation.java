package assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	private int gridSize;
	private WeightedQuickUnionUF wQU;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		grid = new boolean[N][N];
		gridSize = N;
		wQU = new WeightedQuickUnionUF(N * N + 2);
	}

	// convert to 1D
	private int xyTo1D(int x, int y) {
		return (x-1) * gridSize + y;
	}

	// validate index
	private void validateIndex(int x, int y) {
		if (x <= 0 || x > gridSize || y <= 0 || y > gridSize)
			throw new IndexOutOfBoundsException("row index i out of bounds");
	}

	// open site (row x, column y) if it is not open already
	public void open(int x, int y) {
		validateIndex(x, y);
		grid[x - 1][y - 1] = true;

		// check top virtual
		if (x == 1) {
			wQU.union(0, xyTo1D(x, y));
		}

		// check bottom virtual
		if (x == gridSize) {
			wQU.union(gridSize * gridSize + 1, xyTo1D(x, y));
		}

		// check top
		if (x != 1 && isOpen(x - 1, y)) {
			wQU.union(xyTo1D(x, y), xyTo1D(x - 1, y));
		}

		// check bottom
		if (x != gridSize && isOpen(x + 1, y)) {
			wQU.union(xyTo1D(x, y), xyTo1D(x + 1, y));
		}

		// check right
		if (y != gridSize && isOpen(x, y + 1)) {
			wQU.union(xyTo1D(x, y), xyTo1D(x, y + 1));
		}

		// check left
		if (y != 1 && isOpen(x, y - 1)) {
			wQU.union(xyTo1D(x, y), xyTo1D(x, y - 1));
		}

	}

	// is site (row x, column y) open?
	public boolean isOpen(int x, int y) {
		validateIndex(x, y);
		return grid[x - 1][y - 1];
	}

	// is site (row i, column j) full?
	public boolean isFull(int x, int y) {
        return wQU.connected(0, xyTo1D(x, y));
	}
	
	 public boolean percolates(){
		return wQU.connected(0, gridSize * gridSize + 1);		 
	 }
	

	// test client (optional)
	public static void main(String[] args) {
		Percolation p = new Percolation(4);
		p.open(1, 1);
		p.open(2, 2);
		p.open(3, 3);
		p.open(4, 4);
		p.open(2, 1);
		p.open(3, 2);
		p.open(4, 3);

		System.out.println(p.percolates());

	}
}
