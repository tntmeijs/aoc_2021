package dev.tahar.puzzle.day.three;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DayThree extends BasePuzzle<String> {

    /**
     * Create a new puzzle solver for day three
     */
    public DayThree() {
        super(PuzzleDay.THREE, "Binary Diagnostic", input -> input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        // Key: bit index
        // Value: number of high bit occurrences at this position
        final var highBitOccurrences = new HashMap<Integer, Integer>();

        for (final var line : puzzleInput) {
            for (int bitIndex = 0; bitIndex < line.length(); ++bitIndex) {
                if (line.charAt(bitIndex) == '1') {
                    highBitOccurrences.put(bitIndex, highBitOccurrences.getOrDefault(bitIndex, 0) + 1);
                }
            }
        }

        final var gammaBuilder = new StringBuilder();
        final var epsilonBuilder = new StringBuilder();

        // If high bits occur more than half of the total puzzle input size, the most common bit is high, else, low
        for (final var occurrence : highBitOccurrences.values()) {
            if (occurrence > puzzleInput.size() / 2) {
                gammaBuilder.append('1');
                epsilonBuilder.append('0');
            } else {
                gammaBuilder.append('0');
                epsilonBuilder.append('1');
            }
        }

        final var gamma = Integer.parseInt(gammaBuilder.toString(), 2);
        final var epsilon = Integer.parseInt(epsilonBuilder.toString(), 2);

        return "Power consumption: " + (gamma * epsilon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        final var oxygenRating = Integer.parseInt(reduceUntilRatingFound(puzzleInput, RatingType.OXYGEN), 2);
        final var scrubberRating = Integer.parseInt(reduceUntilRatingFound(puzzleInput, RatingType.SCRUBBER), 2);

        return "Life support rating: " + (oxygenRating * scrubberRating);
    }

    /**
     * Keep applying filtering criteria until one number remains
     *
     * @param bitSequences Bit sequence strings to process
     * @param ratingType   Rating type logic to apply
     * @return Rating value
     */
    private String reduceUntilRatingFound(final List<String> bitSequences, final RatingType ratingType) {
        final var sequenceLength = bitSequences.get(0).length();
        List<String> modifiedSequences = new ArrayList<>(bitSequences);

        while (modifiedSequences.size() > 1) {
            for (int bitIndex = 0; bitIndex < sequenceLength; ++bitIndex) {
                if (modifiedSequences.size() == 1) {
                    break;
                }

                modifiedSequences = applyBitCriteria(modifiedSequences, bitIndex, ratingType);
            }
        }

        return modifiedSequences.get(0);
    }

    /**
     * Apply bit filtering criteria
     *
     * @param bitSequences Bit sequence strings to process
     * @param bitIndex     Bit index to filter
     * @param ratingType   Rating type logic to apply
     * @return Rating value
     */
    private List<String> applyBitCriteria(final List<String> bitSequences, final int bitIndex, final RatingType ratingType) {
        final var mostCommonBit = findMostCommonBitAtIndex(bitSequences, bitIndex);
        final var leastCommonBit = mostCommonBit == '1' ? '0' : '1';

        final var result = new ArrayList<>(bitSequences);

        if (RatingType.OXYGEN.equals(ratingType)) {
            // Keep numbers with the most common bit at the start
            result.removeIf(bitSequence -> bitSequence.charAt(bitIndex) == leastCommonBit);
        } else {
            // Keep number with the least common bit at the start
            result.removeIf(bitSequence -> bitSequence.charAt(bitIndex) == mostCommonBit);
        }

        return result;
    }

    /**
     * Helper method to find the most common bit at the specified index of each entry in the bit sequences
     *
     * @param bitSequences Bit sequence strings to process
     * @param bitIndex     Bit index to check
     * @return One ('1') if the number of high bits are greater than or equal to the number of low bits, zero ('0') otherwise
     */
    private char findMostCommonBitAtIndex(final List<String> bitSequences, final int bitIndex) {
        int numHighBits = 0;

        for (final var bitSequence : bitSequences) {
            if (bitSequence.charAt(bitIndex) == '1') {
                ++numHighBits;
            }
        }

        return numHighBits * 2 >= bitSequences.size() ? '1' : '0';
    }

}
