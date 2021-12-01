package dev.tahar.puzzle;

import dev.tahar.utility.PuzzleInputParser;

import java.util.List;

public abstract class BasePuzzle {

    private final String title;

    protected final List<String> puzzleInput;

    public BasePuzzle(final int day, final String title) {
        this.title = title;
        puzzleInput = new PuzzleInputParser().loadInput(day);
    }

    public abstract String executePartOne();

    public abstract String executePartTwo();

    public String getTitle() {
        return title;
    }

}
