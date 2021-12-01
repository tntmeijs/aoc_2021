package dev.tahar.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Utility class to help simplify loading puzzle input from disk
 */
public final class PuzzleInputParser {

    /**
     * Load a puzzle's input from disk by puzzle day index
     *
     * @param day Day index (1-based)
     * @return {@link List} with puzzle input
     */
    public List<String> loadInput(final int day) {
        final var path = String.format("/%d.txt", day);
        final var resource = Optional.ofNullable(this.getClass().getResourceAsStream(path));

        if (resource.isEmpty()) {
            return Collections.emptyList();
        }

        final var puzzleInput = new ArrayList<String>();

        try (final var reader = new BufferedReader(new InputStreamReader(resource.get()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                puzzleInput.add(line);
            }
        } catch (final IOException ioException) {
            System.out.println("Unable to read puzzle input from file");
        }

        return puzzleInput;
    }

}
