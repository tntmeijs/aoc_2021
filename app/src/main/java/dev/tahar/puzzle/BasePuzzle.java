package dev.tahar.puzzle;

import dev.tahar.utility.PuzzleInputParser;

import java.util.List;

/**
 * Base class for all puzzles, provides common puzzle functionality
 */
public abstract class BasePuzzle {

    /**
     * Title of the puzzle
     */
    private final String title;

    /**
     * Input of the puzzle
     */
    protected final List<String> puzzleInput;

    /**
     * Construct a new {@link BasePuzzle}
     *
     * @param day   The day this puzzle belongs to
     * @param title Title of the puzzle
     */
    public BasePuzzle(final PuzzleDay day, final String title) {
        this.title = title;
        puzzleInput = new PuzzleInputParser().loadInput(day.ordinal());
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
