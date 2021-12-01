package dev.tahar.puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Base class for all puzzles, provides common puzzle functionality
 *
 * @param <InputType> This is the type used to interpret the input file's puzzle data
 */
public abstract class BasePuzzle<InputType> {

    /**
     * Title of the puzzle
     */
    private final String title;

    /**
     * Input of the puzzle
     */
    protected final List<InputType> puzzleInput;

    /**
     * Construct a new {@link BasePuzzle}
     *
     * @param day       The day this puzzle belongs to
     * @param title     Title of the puzzle
     * @param inputType This is the type used to interpret the input file's puzzle data
     * @param cast      Function to cast the input into {@link InputType}
     */
    public BasePuzzle(final PuzzleDay day, final String title, Function<String, InputType> cast) {
        this.title = title;
        puzzleInput = loadInput(day, cast);
    }

    /**
     * Execute the first part of the puzzle
     *
     * @return String to print on success - usually this will be a string with the answer to the puzzle
     */
    public abstract String executePartOne();

    /**
     * Execute the second part of the puzzle
     *
     * @return String to print on success - usually this will be a string with the answer to the puzzle
     */
    public abstract String executePartTwo();

    /**
     * Return the title of the puzzle
     *
     * @return Puzzle's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Load a puzzle's input from disk
     *
     * @param day  Day to load puzzle data for
     * @param cast Function to cast the input into {@link InputType}
     * @return {@link List} of {@link InputType} with puzzle input data
     */
    private List<InputType> loadInput(final PuzzleDay day, Function<String, InputType> cast) {
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
