package dev.tahar.puzzle.day.four;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class BingoCard {

    /**
     * Predefines width and height of a bingo card
     */
    private final static int CARD_SIZE = 5;

    /**
     * Unique id that identifies this bingo card
     */
    private final UUID id;

    /**
     * Bingo card data stored row-major order
     */
    private final BingoEntry[][] card;

    /**
     * Initialize a new {@link BingoCard} instance from a list of strings (puzzle input)
     *
     * @param cardAsString List of strings - each string represents a row
     */
    public BingoCard(final List<String> cardAsString) {
        id = UUID.randomUUID();
        card = new BingoEntry[CARD_SIZE][CARD_SIZE];

        // Initialize a new bingo card
        for (int row = 0; row < CARD_SIZE; ++row) {
            final var rowString = cardAsString.get(row);

            final var columnValues = Arrays
                    .stream(rowString.split(" "))
                    .filter(string -> !string.isBlank())
                    .map(Integer::parseInt)
                    .map(BingoEntry::new)
                    .collect(Collectors.toList());

            for (int column = 0; column < CARD_SIZE; ++column) {
                card[row][column] = columnValues.get(column);
            }
        }
    }

    /**
     * Get this bingo card's unique id
     *
     * @return {@link UUID} that identifies this card
     */
    public UUID getId() {
        return id;
    }

    /**
     * Reset the "marked" status of all entries of this bingo card
     */
    public void reset() {
        for (final var row : card) {
            for (final var column : row) {
                column.setMarked(false);
            }
        }

    }

    /**
     * Mark a field as "marked" if the bingo card contains this number
     *
     * @param number Number to check
     */
    public void markNumberIfPresent(final int number) {
        for (final var row : card) {
            for (final var column : row) {
                if (column.getNumber() == number) {
                    column.setMarked(true);
                    return;
                }
            }
        }
    }

    /**
     * Check if this bingo card wins
     *
     * @return True if this card wins, false if not
     */
    public boolean hasBingo() {
        return hasAtLeastOneWinningRow() || hasAtLeastOneWinningColumn();
    }

    /**
     * Calculate the bingo card's score
     *
     * @return Score of this bingo card
     */
    public int calculateScore() {
        int sum = 0;

        for (final var row : card) {
            for (final var column : row) {
                if (!column.getMarked()) {
                    sum += column.getNumber();
                }
            }
        }

        return sum;
    }

    /**
     * Utility method to check whether this bingo card contains any winning rows
     *
     * @return True if this bingo card has any winning rows, false when not
     */
    private boolean hasAtLeastOneWinningRow() {
        for (int column = 0; column < CARD_SIZE; ++column) {
            int marked = 0;

            for (int row = 0; row < CARD_SIZE; ++row) {
                if (card[row][column].getMarked()) {
                    ++marked;
                }
            }

            // Found a row in which all entries were marked
            if (marked == CARD_SIZE) {
                return true;
            }
        }

        // No winning rows were found
        return false;
    }

    /**
     * Utility method to check whether this bingo card contains any winning columns
     *
     * @return True if this bingo card has any winning columns, false when not
     */
    private boolean hasAtLeastOneWinningColumn() {
        for (int row = 0; row < CARD_SIZE; ++row) {
            int marked = 0;

            for (int column = 0; column < CARD_SIZE; ++column) {
                if (card[row][column].getMarked()) {
                    ++marked;
                }

                // Found a column in which all entries were marked
                if (marked == CARD_SIZE) {
                    return true;
                }
            }
        }

        // No winning columns were found
        return false;
    }

}
