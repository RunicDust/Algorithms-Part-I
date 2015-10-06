package assignment3;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	private Point[] pointsCopy;
	private ArrayList<LineSegment> lineSegments;

	public BruteCollinearPoints(Point[] points) {
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
		for (int p = 0; p < pointsCopy.length - 3; p++) {
			for (int q = p + 1; q < pointsCopy.length - 2; q++) {
				for (int r = q + 1; r < pointsCopy.length - 1; r++) {
					if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r])) {
						for (int s = r + 1; s < pointsCopy.length; s++) {
							if (pointsCopy[p].slopeTo(pointsCopy[r]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
								lineSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));

							}

						}
					}

				}
			}
		}

	}

	// the line segments
	public LineSegment[] segments() {
		LineSegment[] lineSegmentsArray = new LineSegment[lineSegments.size()];
		lineSegmentsArray = lineSegments.toArray(lineSegmentsArray);
		return lineSegmentsArray;
	}

	// the number of line segments
	public int numberOfSegments() {
		return lineSegments.size();
	}

}
