package assignment3;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
	private Point[] pointsCopy;
	private ArrayList<LineSegment> lineSegments;

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}

		pointsCopy = points.clone();
		Arrays.sort(pointsCopy);
		for (int i = 1; i < pointsCopy.length; i++) {
			if (pointsCopy[i - 1].equals(pointsCopy[i])) {
				throw new java.lang.IllegalArgumentException();
			}
		}

		lineSegments = new ArrayList<>();
		for (Point point : points) {
			Arrays.sort(pointsCopy); // can you please explain why this line is necessary ? Since the pointCopy is sorted by the comparator next.
			Arrays.sort(pointsCopy, point.slopeOrder());
			int first = 0;
			int last = 0;

			while (last < pointsCopy.length) {
				while (last < pointsCopy.length && Double.compare(pointsCopy[0].slopeTo(pointsCopy[first]),
						pointsCopy[0].slopeTo(pointsCopy[last])) == 0) {
					last++;
				}
				// if found at least 3 elements, make segment if it's unique
				if (last - first >= 3 && pointsCopy[0].compareTo(pointsCopy[first]) < 0) {
					lineSegments.add(new LineSegment(pointsCopy[0], pointsCopy[last - 1]));
				}

				// Try to find next
				first = last;
				last++;
			}
		}

	}

	// the number of line segments
	public int numberOfSegments() {
		return lineSegments.size();
	}

	// the line segments
	public LineSegment[] segments() {
		LineSegment[] lineSegmentsArray = new LineSegment[lineSegments.size()];
		lineSegmentsArray = lineSegments.toArray(lineSegmentsArray);
		return lineSegmentsArray;
	}

	public static void main(String[] args) {

	}
}
