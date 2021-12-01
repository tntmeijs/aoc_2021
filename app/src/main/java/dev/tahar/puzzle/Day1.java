package dev.tahar.puzzle;

public final class Day1 extends BasePuzzle {

    public Day1() {
        super(1, "Sonar Sweep");
    }

    @Override
    public String executePartOne() {
        int numLargerThanPrevious = 0;
        int previousValue = Integer.parseInt(puzzleInput.get(0));

        for (int i = 1; i < puzzleInput.size(); ++i) {
            final var value = Integer.parseInt(puzzleInput.get(i));

            if (value > previousValue) {
                ++numLargerThanPrevious;
            }

            previousValue = value;
        }

        return String.format("Encountered %d measurements larger than the previous one", numLargerThanPrevious);
    }

    @Override
    public String executePartTwo() {
        return "";
    }

}
