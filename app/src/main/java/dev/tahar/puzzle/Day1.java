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
        int numLargerThanPrevious = 0;
        Integer previousSum = null; // Initial state is always invalid

        for (int i = 0; i < puzzleInput.size() - 2; ++i) {
            final var rollingWindow0 = Integer.parseInt(puzzleInput.get(i));
            final var rollingWindow1 = Integer.parseInt(puzzleInput.get(i + 1));
            final var rollingWindow2 = Integer.parseInt(puzzleInput.get(i + 2));

            final var sum = rollingWindow0 + rollingWindow1 + rollingWindow2;

            if (previousSum != null && sum > previousSum) {
                ++numLargerThanPrevious;
            }

            previousSum = sum;
        }

        return String.format("Encountered %d sums larger than the previous one", numLargerThanPrevious);
    }

}
