package dev.tahar.puzzle.day.six;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaySix extends BasePuzzle<List<Integer>> {

    /**
     * Create a new puzzle solver for day six
     */
    public DaySix() {
        super(PuzzleDay.SIX, "Lanternfish", input -> Arrays
                .stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        return String.format("After 80 days there are a total of %d fish", calculateNumberOfFish(80, loadInput()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        return String.format("After 256 days there are a total of %d fish", calculateNumberOfFish(256, loadInput()));
    }

    /**
     * Load the puzzle's input in a usable data structure
     *
     * @return {@link Map} of fish's timers and the number of fish with that timer value
     */
    private Map<Integer, Long> loadInput() {
        // Each key represents a fish's timer (0 to 8), while its value represents how many fish are of that age
        final var fish = new HashMap<>(Map.of(
                0, 0L,
                1, 0L,
                2, 0L,
                3, 0L,
                4, 0L,
                5, 0L,
                6, 0L,
                7, 0L,
                8, 0L));

        // Add all fish to the tracker
        puzzleInput.get(0).forEach(age -> {
            fish.put(age, fish.get(age) + 1L);
        });

        return fish;
    }

    /**
     * Calculate how many fish there will be in the ocean after N days
     *
     * @param days Number of days to simulate
     * @param fish Starting state of the fish (parsed puzzle input)
     * @return Number of fish in the ocean after N days
     */
    private long calculateNumberOfFish(final int days, final Map<Integer, Long> fish) {
        for (int day = 0; day < days; ++day) {
            // All fish with a timer 0 produce offspring
            final var offspring = fish.get(0);

            // Increase fish's timers
            fish.put(0, fish.get(1));
            fish.put(1, fish.get(2));
            fish.put(2, fish.get(3));
            fish.put(3, fish.get(4));
            fish.put(4, fish.get(5));
            fish.put(5, fish.get(6));
            fish.put(6, fish.get(7));
            fish.put(7, fish.get(8));

            // All newborn fish start with a timer of 8 days
            fish.put(8, offspring);

            // In addition to moving up one spot each day, all fish that produced newborn fish need their timer reset to 6 days again
            fish.put(6, fish.get(6) + offspring);
        }

        // Find the total number of fish by summing all fish in the map
        return fish
                .values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

}
