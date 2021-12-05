package dev.tahar.puzzle.day.five;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record LineSegment(Coordinate start, Coordinate end) {

    /**
     * Returns whether a line segment is a horizontal line
     *
     * @return True if the line is horizontal, false if not
     */
    public boolean isHorizontal() {
        // A line horizontal if the Y coordinate remains the same
        return start.y() == end.y();
    }

    /**
     * Returns whether a line segment is a vertical line
     *
     * @return True if the line is vertical, false if not
     */
    public boolean isVertical() {
        // A line vertical if the X coordinate remains the same
        return start.x() == end.x();
    }

    /**
     * Returns whether a line segment is a 45 degree diagonal line
     *
     * @return True if the line is a 45 degree diagonal line, false if not
     */
    public boolean isDiagonal() {
        // A line is diagonal if ΔX == ΔY
        return Math.abs(start.x() - end.x()) == Math.abs(start.y() - end.y());
    }

    /**
     * Return a list of all coordinates along a horizontal line segment
     *
     * @return {@link List} of {@link Coordinate} objects
     */
    public List<Coordinate> getHorizontalCoordinatesAlongSegment() {
        if (!isHorizontal()) {
            return Collections.emptyList();
        }

        if (end().x() < start().x()) {
            // Ensure "backwards" segments are also considered
            return IntStream
                    .rangeClosed(end().x(), start().x())
                    .mapToObj(x -> new Coordinate(x, start().y()))
                    .collect(Collectors.toList());
        } else {
            return IntStream
                    .rangeClosed(start().x(), end().x())
                    .mapToObj(x -> new Coordinate(x, start().y()))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Return a list of all coordinates along a vertical line segment
     *
     * @return {@link List} of {@link Coordinate} objects
     */
    public List<Coordinate> getVerticalCoordinatesAlongSegment() {
        if (!isVertical()) {
            return Collections.emptyList();
        }

        if (end().y() < start().y()) {
            // Ensure "backwards" segments are also considered
            return IntStream
                    .rangeClosed(end().y(), start().y())
                    .mapToObj(y -> new Coordinate(start().x(), y))
                    .collect(Collectors.toList());
        } else {
            return IntStream
                    .rangeClosed(start().y(), end().y())
                    .mapToObj(y -> new Coordinate(start().x(), y))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Return a list of all coordinates along a diagonal line segment
     *
     * @return {@link List} of {@link Coordinate} objects
     */
    public List<Coordinate> getDiagonalCoordinatesAlongSegment() {
        if (!isDiagonal()) {
            return Collections.emptyList();
        }

        final var horizontalDirection = (end().x() - start.x()) < 0 ? -1 : 1;
        final var verticalDirection = (end().y() - start.y()) < 0 ? -1 : 1;

        int x = start.x();
        int y = start.y();

        final var coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(x, y));

        // Loop until we reach the target coordinate (inclusive)
        // Since the diagonals are 45 degrees, the target destination has been reached if either one of the coordinates reaches the end
        do {
            x += horizontalDirection;
            y += verticalDirection;
            coordinates.add(new Coordinate(x, y));
        } while (x != end.x());

        return coordinates;
    }

}
