package dev.tahar.utility;

import dev.tahar.puzzle.PuzzleDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Utility class to help simplify loading puzzle input from disk
 *
 * @param <InputType> Type to cast each line of the input file into
 */
public final class PuzzleInputParser<InputType> {

    /**
     * Type to store the puzzle input data as
     */
    private final Class<InputType> clazz;

    public PuzzleInputParser(final Class<InputType> clazz) {
        this.clazz = clazz;
    }

    /**
     * Load a puzzle's input from disk
     *
     * @param day  Day to load puzzle data for
     * @param cast Function to cast the input into {@link InputType}
     * @return {@link List} of {@link InputType} with puzzle input data
     */
    public List<InputType> loadInput(final PuzzleDay day, Function<String, InputType> cast) {
        final var path = String.format("/%d.txt", day.ordinal() + 1);
        final var resource = Optional.ofNullable(this.getClass().getResourceAsStream(path));

        if (resource.isEmpty()) {
            System.out.println("Unable to parse puzzle input - no resource file found");
            return Collections.emptyList();
        }

        final var puzzleInput = new ArrayList<InputType>();

        // Load a file from disk - each file is expected to be formatted like so: <int>.txt
        // Every <int> should correspond to that day's "index"
        // For example, 8.txt implies that 8.txt holds the puzzle data for the 8th of December
        try (final var reader = new BufferedReader(new InputStreamReader(resource.get()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Before storing the line, first run the custom cast function on it to turn the line into the proper
                // format needed by the puzzle
                puzzleInput.add(cast.apply(line));
            }
        } catch (final IOException ioException) {
            System.out.println("Unable to read puzzle input from file");
        }

        return puzzleInput;
    }

}
