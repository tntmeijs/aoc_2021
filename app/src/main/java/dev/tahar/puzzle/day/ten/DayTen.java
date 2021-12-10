package dev.tahar.puzzle.day.ten;

import dev.tahar.puzzle.BasePuzzle;
import dev.tahar.puzzle.PuzzleDay;

import java.util.*;
import java.util.stream.Collectors;

public final class DayTen extends BasePuzzle<String> {

    /**
     * Block open characters mapped to their corresponding closing characters
     */
    private final static Map<Character, Character> PAIR_LOOKUP_TABLE = Map.of(
            '(', ')',
            '[', ']',
            '{', '}',
            '<', '>');

    /**
     * Valid characters that indicate the opening of a block
     */
    private final static Set<Character> VALID_OPEN_CHARACTERS = Set.of('(', '[', '{', '<');

    /**
     * Create a new puzzle solver for day ten
     */
    public DayTen() {
        super(PuzzleDay.TEN, "Syntax Scoring", input -> input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartOne() {
        int score = 0;

        for (final var line : puzzleInput) {
            final var corruptCharacter = checkLineForCorruptCharacters(line);

            if (corruptCharacter.isPresent()) {
                switch (corruptCharacter.get()) {
                    case ')' -> score += 3;
                    case ']' -> score += 57;
                    case '}' -> score += 1197;
                    case '>' -> score += 25137;
                }
            }
        }

        return "Total syntax error score is " + score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executePartTwo() {
        final var scores = new ArrayList<Long>();

        // Remove all corrupted lines to end up with all incorrect lines instead
        final var incorrectLines = puzzleInput
                .stream()
                .filter(line -> checkLineForCorruptCharacters(line).isEmpty())
                .collect(Collectors.toList());

        for (final var line : incorrectLines) {
            final var missingCharacters = findMissingClosingCharacters(line);

            long score = 0;
            for (final var character : missingCharacters) {
                score *= 5;

                switch (character) {
                    case ')' -> score += 1;
                    case ']' -> score += 2;
                    case '}' -> score += 3;
                    case '>' -> score += 4;
                }
            }

            scores.add(score);
        }

        scores.sort(Long::compareTo);

        return "Syntax error score of the middle score is " + scores.get((int) Math.floor(scores.size() / 2.0d));
    }

    /**
     * Check a line for corrupt characters (invalid bracket ordering)
     *
     * @param line Line to check
     * @return Optional character, empty if the line did not contain any corrupt characters
     */
    public Optional<Character> checkLineForCorruptCharacters(final String line) {
        final var openCharacters = new ArrayDeque<Character>();

        for (int i = 0; i < line.length(); ++i) {
            final var characterToTest = line.charAt(i);

            if (VALID_OPEN_CHARACTERS.contains(characterToTest)) {
                // Character is a block open character - these can always be appended
                openCharacters.add(characterToTest);
            } else {
                // Character is a block close character - the only valid character is one that matches the open character
                final var mostRecentOpenCharacter = openCharacters.peekLast();

                if (mostRecentOpenCharacter != null && PAIR_LOOKUP_TABLE.get(mostRecentOpenCharacter).equals(characterToTest)) {
                    // Correct close character
                    openCharacters.removeLast();
                } else {
                    return Optional.of(characterToTest);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Find a list of missing closing characters from a line of brackets
     *
     * @param line Line to check
     * @return List of missing closing characters
     */
    public List<Character> findMissingClosingCharacters(final String line) {
        final var openCharacters = new ArrayDeque<Character>();

        for (int i = 0; i < line.length(); ++i) {
            final var characterToTest = line.charAt(i);

            if (VALID_OPEN_CHARACTERS.contains(characterToTest)) {
                // Character is a block open character - these can always be appended
                openCharacters.add(characterToTest);
            } else {
                // Character is a block close character - the only valid character is one that matches the open character
                final var mostRecentOpenCharacter = openCharacters.peekLast();

                if (mostRecentOpenCharacter != null && PAIR_LOOKUP_TABLE.get(mostRecentOpenCharacter).equals(characterToTest)) {
                    // Correct close character
                    openCharacters.removeLast();
                }
            }
        }

        // All remaining open characters have not been closed properly - these can be used to determine the missing closing characters
        // Also flip the order as close characters should be the opposite from open characters
        final var openCharactersList = openCharacters.stream().toList();
        final var closeCharacters = new ArrayList<Character>();

        for (int i = openCharacters.size() - 1; i >= 0; --i) {
            closeCharacters.add(PAIR_LOOKUP_TABLE.get(openCharactersList.get(i)));
        }

        return closeCharacters;
    }

}
