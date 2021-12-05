package dev.tahar.day.five;

import dev.tahar.puzzle.day.five.Coordinate;
import dev.tahar.puzzle.day.five.LineSegment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineSegmentTests {

    @Test
    void testIsHorizontal() {
        final var segment = new LineSegment(new Coordinate(0, 5), new Coordinate(5, 5));
        Assertions.assertTrue(segment.isHorizontal());
    }

    @Test
    void testIsNotHorizontal() {
        final var segment = new LineSegment(new Coordinate(0, 4), new Coordinate(5, 5));
        Assertions.assertFalse(segment.isHorizontal());
    }

    @Test
    void testIsHorizontalBackwards() {
        final var segment = new LineSegment(new Coordinate(5, 5), new Coordinate(0, 5));
        Assertions.assertTrue(segment.isHorizontal());
    }

    @Test
    void testIsVertical() {
        final var segment = new LineSegment(new Coordinate(5, 0), new Coordinate(5, 5));
        Assertions.assertTrue(segment.isVertical());
    }

    @Test
    void testIsNotVertical() {
        final var segment = new LineSegment(new Coordinate(4, 0), new Coordinate(5, 5));
        Assertions.assertFalse(segment.isVertical());
    }

    @Test
    void testIsVerticalBackwards() {
        final var segment = new LineSegment(new Coordinate(5, 5), new Coordinate(5, 0));
        Assertions.assertTrue(segment.isVertical());
    }

    @Test
    void testIsDiagonal() {
        final var segment = new LineSegment(new Coordinate(4, 2), new Coordinate(6, 4));
        Assertions.assertTrue(segment.isDiagonal());
    }

    @Test
    void testIsNotDiagonal() {
        final var segment = new LineSegment(new Coordinate(4, 0), new Coordinate(5, 5));
        Assertions.assertFalse(segment.isDiagonal());
    }

    @Test
    void testIsDiagonalBackwards() {
        final var segment = new LineSegment(new Coordinate(2, 4), new Coordinate(4, 6));
        Assertions.assertTrue(segment.isDiagonal());
    }

    @Test
    void testListHorizontalCoordinatesAlongSegment() {
        final var segment = new LineSegment(new Coordinate(0, 5), new Coordinate(5, 5));
        final var coordinates = segment.getHorizontalCoordinatesAlongSegment();

        Assertions.assertEquals(0, coordinates.get(0).x());
        Assertions.assertEquals(1, coordinates.get(1).x());
        Assertions.assertEquals(2, coordinates.get(2).x());
        Assertions.assertEquals(3, coordinates.get(3).x());
        Assertions.assertEquals(4, coordinates.get(4).x());
        Assertions.assertEquals(5, coordinates.get(5).x());

        Assertions.assertEquals(5, coordinates.get(0).y());
        Assertions.assertEquals(5, coordinates.get(1).y());
        Assertions.assertEquals(5, coordinates.get(2).y());
        Assertions.assertEquals(5, coordinates.get(3).y());
        Assertions.assertEquals(5, coordinates.get(4).y());
        Assertions.assertEquals(5, coordinates.get(5).y());
    }

    @Test
    void testListHorizontalCoordinatesAlongSegmentBackwards() {
        final var segment = new LineSegment(new Coordinate(5, 5), new Coordinate(0, 5));
        final var coordinates = segment.getHorizontalCoordinatesAlongSegment();

        Assertions.assertEquals(0, coordinates.get(0).x());
        Assertions.assertEquals(1, coordinates.get(1).x());
        Assertions.assertEquals(2, coordinates.get(2).x());
        Assertions.assertEquals(3, coordinates.get(3).x());
        Assertions.assertEquals(4, coordinates.get(4).x());
        Assertions.assertEquals(5, coordinates.get(5).x());

        Assertions.assertEquals(5, coordinates.get(0).y());
        Assertions.assertEquals(5, coordinates.get(1).y());
        Assertions.assertEquals(5, coordinates.get(2).y());
        Assertions.assertEquals(5, coordinates.get(3).y());
        Assertions.assertEquals(5, coordinates.get(4).y());
        Assertions.assertEquals(5, coordinates.get(5).y());
    }

    @Test
    void testListHorizontalCoordinatesAlongSegmentNone() {
        final var segment = new LineSegment(new Coordinate(5, 0), new Coordinate(5, 5));
        final var coordinates = segment.getHorizontalCoordinatesAlongSegment();

        Assertions.assertTrue(coordinates.isEmpty());
    }

    @Test
    void testListVerticalCoordinatesAlongSegment() {
        final var segment = new LineSegment(new Coordinate(5, 0), new Coordinate(5, 5));
        final var coordinates = segment.getVerticalCoordinatesAlongSegment();

        Assertions.assertEquals(0, coordinates.get(0).y());
        Assertions.assertEquals(1, coordinates.get(1).y());
        Assertions.assertEquals(2, coordinates.get(2).y());
        Assertions.assertEquals(3, coordinates.get(3).y());
        Assertions.assertEquals(4, coordinates.get(4).y());
        Assertions.assertEquals(5, coordinates.get(5).y());

        Assertions.assertEquals(5, coordinates.get(0).x());
        Assertions.assertEquals(5, coordinates.get(1).x());
        Assertions.assertEquals(5, coordinates.get(2).x());
        Assertions.assertEquals(5, coordinates.get(3).x());
        Assertions.assertEquals(5, coordinates.get(4).x());
        Assertions.assertEquals(5, coordinates.get(5).x());
    }

    @Test
    void testListVerticalCoordinatesAlongSegmentBackwards() {
        final var segment = new LineSegment(new Coordinate(5, 5), new Coordinate(5, 0));
        final var coordinates = segment.getVerticalCoordinatesAlongSegment();

        Assertions.assertEquals(0, coordinates.get(0).y());
        Assertions.assertEquals(1, coordinates.get(1).y());
        Assertions.assertEquals(2, coordinates.get(2).y());
        Assertions.assertEquals(3, coordinates.get(3).y());
        Assertions.assertEquals(4, coordinates.get(4).y());
        Assertions.assertEquals(5, coordinates.get(5).y());

        Assertions.assertEquals(5, coordinates.get(0).x());
        Assertions.assertEquals(5, coordinates.get(1).x());
        Assertions.assertEquals(5, coordinates.get(2).x());
        Assertions.assertEquals(5, coordinates.get(3).x());
        Assertions.assertEquals(5, coordinates.get(4).x());
        Assertions.assertEquals(5, coordinates.get(5).x());
    }

    @Test
    void testListVerticalCoordinatesAlongSegmentNone() {
        final var segment = new LineSegment(new Coordinate(0, 5), new Coordinate(5, 5));
        final var coordinates = segment.getVerticalCoordinatesAlongSegment();

        Assertions.assertTrue(coordinates.isEmpty());
    }

    @Test
    void testListDiagonalCoordinatesAlongSegment() {
        final var segment = new LineSegment(new Coordinate(2, 4), new Coordinate(6, 8));
        final var coordinates = segment.getDiagonalCoordinatesAlongSegment();

        Assertions.assertEquals(2, coordinates.get(0).x());
        Assertions.assertEquals(4, coordinates.get(0).y());

        Assertions.assertEquals(3, coordinates.get(1).x());
        Assertions.assertEquals(5, coordinates.get(1).y());

        Assertions.assertEquals(4, coordinates.get(2).x());
        Assertions.assertEquals(6, coordinates.get(2).y());

        Assertions.assertEquals(5, coordinates.get(3).x());
        Assertions.assertEquals(7, coordinates.get(3).y());

        Assertions.assertEquals(6, coordinates.get(4).x());
        Assertions.assertEquals(8, coordinates.get(4).y());
    }

    @Test
    void testListDiagonalCoordinatesAlongSegmentBackwards() {
        final var segment = new LineSegment(new Coordinate(6, 8), new Coordinate(2, 4));
        final var coordinates = segment.getDiagonalCoordinatesAlongSegment();

        Assertions.assertEquals(6, coordinates.get(0).x());
        Assertions.assertEquals(8, coordinates.get(0).y());

        Assertions.assertEquals(5, coordinates.get(1).x());
        Assertions.assertEquals(7, coordinates.get(1).y());

        Assertions.assertEquals(4, coordinates.get(2).x());
        Assertions.assertEquals(6, coordinates.get(2).y());

        Assertions.assertEquals(3, coordinates.get(3).x());
        Assertions.assertEquals(5, coordinates.get(3).y());

        Assertions.assertEquals(2, coordinates.get(4).x());
        Assertions.assertEquals(4, coordinates.get(4).y());
    }

    @Test
    void testListDiagonalCoordinatesAlongSegmentNone() {
        final var segment = new LineSegment(new Coordinate(0, 5), new Coordinate(5, 5));
        final var coordinates = segment.getDiagonalCoordinatesAlongSegment();

        Assertions.assertTrue(coordinates.isEmpty());
    }

}
