package dev.tahar.puzzle.day.one;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

import java.util.Arrays;

public final class DayOne extends BasePuzzle<Integer> {

    /**
     * Create a new puzzle solver for day one
     */
    public DayOne() {
        super(PuzzleDay.ONE, "Sonar Sweep", Integer::parseInt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        int numLargerThanPrevious = 0;
        int previousValue = puzzleInput.get(0);

        for (int i = 1; i < puzzleInput.size(); ++i) {
            final var value = puzzleInput.get(i);

            if (value > previousValue) {
                ++numLargerThanPrevious;
            }

            previousValue = value;
        }

        return String.format("Encountered %d measurements larger than the previous one", numLargerThanPrevious);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        int numLargerThanPrevious = 0;

        // Impossible to have a previous sum on the first iteration of the rolling window
        Integer previousSum = null;

        // Rolling window
        for (int i = 0; i < puzzleInput.size() - 2; ++i) {
            final var window = new int[]{
                    puzzleInput.get(i),
                    puzzleInput.get(i + 1),
                    puzzleInput.get(i + 2)
            };

            final var sum = Arrays.stream(window).sum();

            // Keep track of the number of times the sum is larger than the previous sum
            if (previousSum != null && sum > previousSum) {
                ++numLargerThanPrevious;
            }

            previousSum = sum;
        }

        return String.format("Encountered %d sums larger than the previous one", numLargerThanPrevious);
    }

}
