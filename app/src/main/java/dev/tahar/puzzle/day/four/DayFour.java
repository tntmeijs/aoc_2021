package dev.tahar.puzzle.day.four;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

import java.util.*;
import java.util.stream.Collectors;

public final class DayFour extends BasePuzzle<String> {

    /**
     * Stores all numbers that will be drawn
     */
    private final List<Integer> numbers;

    /**
     * Stores all available bingo cards
     */
    private final List<BingoCard> bingoCards;

    /**
     * Create a new puzzle solver for day four
     */
    public DayFour() {
        super(PuzzleDay.FOUR, "Giant Squid", input -> input);

        // First line of the input are the bingo numbers
        numbers = Arrays.stream(puzzleInput.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        // General format after the initial bingo numbers:
        //
        // <newline>
        // row 1
        // row 2
        // row 3
        // row 4
        // row 5
        //
        // Repeat until the end of the file has been reached

        bingoCards = new ArrayList<>();
        for (int i = 1; i < puzzleInput.size(); i += 6) {
            // Relative index zero is not used because it is a newline
            final var rows = List.of(
                    puzzleInput.get(i + 1),
                    puzzleInput.get(i + 2),
                    puzzleInput.get(i + 3),
                    puzzleInput.get(i + 4),
                    puzzleInput.get(i + 5));

            bingoCards.add(new BingoCard(rows));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        resetBingoCards();
        for (final var number : numbers) {
            for (final var card : bingoCards) {
                card.markNumberIfPresent(number);

                // Keep playing until a card wins
                if (!card.hasBingo()) {
                    continue;
                }

                // Found a winning card
                final var score = card.calculateScore();

                // Final score is the board's score + the last drawn number
                return "Final score when choosing the first winning board: " + (score * number);
            }
        }

        return "No winning bingo board found - this should never happen";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        int mostRecentWinningScore = 0;
        final var winningCards = new HashSet<UUID>();

        resetBingoCards();
        for (final var number : numbers) {
            for (final var card : bingoCards) {
                if (winningCards.contains(card.getId())) {
                    // Skip any bingo cards which have already won
                    continue;
                }

                card.markNumberIfPresent(number);

                // Evaluate all boards to find the last board that would win
                if (card.hasBingo()) {
                    // Final score is the board's score + the last drawn number
                    mostRecentWinningScore = card.calculateScore() * number;

                    // No need to check this card again - it has already won
                    winningCards.add(card.getId());
                }
            }
        }

        return "Final score when choosing the last winning board: " + mostRecentWinningScore;
    }

    /**
     * Reset all bingo cards to their initial state (read: all fields will be unmarked)
     */
    private void resetBingoCards() {
        for (final var card : bingoCards) {
            card.reset();
        }
    }

}
