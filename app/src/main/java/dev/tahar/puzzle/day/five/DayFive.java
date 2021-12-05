package dev.tahar.puzzle.day.five;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DayFive extends BasePuzzle<LineSegment> {

    /**
     * Create a new puzzle solver for day five
     */
    public DayFive() {
        super(PuzzleDay.FIVE, "Hydrothermal Venture", input -> {
            // Format: x,y -> x,y
            final var coordinates = Arrays
                    .stream(input.split(" -> "))
                    .map(string -> string.split(","))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());

            // Store the pairs as line segments
            return new LineSegment(
                    new Coordinate(
                            Integer.parseInt(coordinates.get(0)),
                            Integer.parseInt(coordinates.get(1))),
                    new Coordinate(
                            Integer.parseInt(coordinates.get(2)),
                            Integer.parseInt(coordinates.get(3))));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        final var usedCoordinates = new HashMap<Coordinate, Integer>();

        for (final var lineSegment : puzzleInput) {
            List<Coordinate> coordinatesToConsider = new ArrayList<>();

            if (lineSegment.isHorizontal()) {
                coordinatesToConsider = lineSegment.getHorizontalCoordinatesAlongSegment();
            } else if (lineSegment.isVertical()) {
                coordinatesToConsider = lineSegment.getVerticalCoordinatesAlongSegment();
            }

            // Count the number of duplicate coordinates
            for (final var coordinate : coordinatesToConsider) {
                usedCoordinates.put(coordinate, usedCoordinates.getOrDefault(coordinate, 0) + 1);
            }
        }

        // Now that all coordinates have been counted, find the number of coordinates that occurred twice or more
        final var numberOfCoordinatesWithAtLeastTwoOverlaps = usedCoordinates
                .values()
                .stream()
                .filter(i -> i >= 2)
                .mapToInt(Integer::intValue)
                .count();

        return String.format("At %d coordinates did two or more lines overlap", numberOfCoordinatesWithAtLeastTwoOverlaps);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        final var usedCoordinates = new HashMap<Coordinate, Integer>();

        for (final var lineSegment : puzzleInput) {
            List<Coordinate> coordinatesToConsider = new ArrayList<>();

            if (lineSegment.isHorizontal()) {
                coordinatesToConsider = lineSegment.getHorizontalCoordinatesAlongSegment();
            } else if (lineSegment.isVertical()) {
                coordinatesToConsider = lineSegment.getVerticalCoordinatesAlongSegment();
            } else if (lineSegment.isDiagonal()) {
                coordinatesToConsider = lineSegment.getDiagonalCoordinatesAlongSegment();
            }

            // Count the number of duplicate coordinates
            for (final var coordinate : coordinatesToConsider) {
                usedCoordinates.put(coordinate, usedCoordinates.getOrDefault(coordinate, 0) + 1);
            }
        }

        // Now that all coordinates have been counted, find the number of coordinates that occurred twice or more
        final var numberOfCoordinatesWithAtLeastTwoOverlaps = usedCoordinates
                .values()
                .stream()
                .filter(i -> i >= 2)
                .mapToInt(Integer::intValue)
                .count();

        return String.format("At %d coordinates did two or more lines overlap", numberOfCoordinatesWithAtLeastTwoOverlaps);
    }

}
