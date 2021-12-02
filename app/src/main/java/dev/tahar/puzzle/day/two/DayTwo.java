package dev.tahar.puzzle.day.two;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

public final class DayTwo extends BasePuzzle<Movement> {

    /**
     * Create a new puzzle solver for day two
     */
    public DayTwo() {
        super(PuzzleDay.TWO, "Dive!", input -> {
            // Input is formatted like so: "<direction> <amount>"
            final var parts = input.split(" ");
            final var direction = Direction.from(parts[0]);
            final var distance = Integer.parseInt(parts[1]);

            if (direction.isEmpty()) {
                throw new RuntimeException("Unable to read direction from input");
            }

            return new Movement(direction.get(), distance);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        int forward = 0;
        int depth = 0;

        for (final var movement : puzzleInput) {
            switch (movement.direction()) {
                case FORWARD -> {
                    forward += movement.amount();
                }
                case DOWN -> {
                    depth += movement.amount();
                }
                case UP -> {
                    depth -= movement.amount();
                }
            }
        }

        return String.format("Final horizontal position (%d) multiplied by final depth (%d) is %d", forward, depth, forward * depth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        int forward = 0;
        int depth = 0;
        int aim = 0;

        for (final var movement : puzzleInput) {
            switch (movement.direction()) {
                case FORWARD -> {
                    forward += movement.amount();
                    depth += aim * movement.amount();
                }
                case DOWN -> {
                    aim += movement.amount();
                }
                case UP -> {
                    aim -= movement.amount();
                }
            }
        }

        return String.format("Final horizontal position (%d) multiplied by final depth (%d) is %d", forward, depth, forward * depth);
    }

}
