package dev.tahar.puzzle;

import dev.tahar.utility.PuzzleInputParser;

import java.util.List;
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
    public BasePuzzle(final PuzzleDay day, final String title, final Class<InputType> inputType, Function<String, InputType> cast) {
        this.title = title;
        puzzleInput = new PuzzleInputParser<>(inputType).loadInput(day, cast);
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

}
