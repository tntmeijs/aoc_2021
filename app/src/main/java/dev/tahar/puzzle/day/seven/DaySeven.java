package dev.tahar.puzzle.day.seven;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySeven extends BasePuzzle<List<Integer>> {

    /**
     * Create a new puzzle solver for day seven
     */
    public DaySeven() {
        super(PuzzleDay.SEVEN, "The Treachery of Whales", input -> Arrays
                .stream(input.split(","))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        final var positions = puzzleInput.get(0);

        int bestTotalFuel = Integer.MAX_VALUE;

        for (int distance = 0; distance < positions.get(positions.size() - 1); ++distance) {
            int totalFuel = 0;

            // Determine fuel needed to move to the same position
            for (final int crabPosition : positions) {
                totalFuel += Math.abs(crabPosition - distance);
            }

            if (bestTotalFuel > totalFuel) {
                bestTotalFuel = totalFuel;
            }
        }

        return "Minimum amount of fuel needed: " + bestTotalFuel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        final var positions = puzzleInput.get(0);

        int bestTotalFuel = Integer.MAX_VALUE;

        for (int distance = 0; distance < positions.get(positions.size() - 1); ++distance) {
            int totalFuel = 0;

            // Determine fuel needed to move to the same position
            for (final int crabPosition : positions) {
                final int distanceToCover = Math.abs(crabPosition - distance);
                
                // Each step costs one unit more than the previous step
                for (int step = 0; step < distanceToCover; ++step) {
                    totalFuel += step + 1;
                }
            }

            if (bestTotalFuel > totalFuel) {
                bestTotalFuel = totalFuel;
            }
        }

        return "Minimum amount of fuel needed when each step costs one unit more than the previous: " + bestTotalFuel;
    }
}
