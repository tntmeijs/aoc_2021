package dev.tahar.puzzle.day.two;

import java.util.Optional;

/**
 * Represents the direction of movement of the submarine
 */
public enum Direction {

    /**
     * Increase horizontal position
     */
    FORWARD,

    /**
     * Increase depth
     */
    DOWN,

    /**
     * Decrease depth
     */
    UP;

    /**
     * Convert a string into a valid {@link Direction} value
     *
     * @param direction String to convert
     * @return {@link Optional} {@link Direction}, {@link Optional#empty()} if the function failed to convert the value
     */
    public static Optional<Direction> from(final String direction) {
        if (direction.equalsIgnoreCase("forward")) {
            return Optional.of(FORWARD);
        } else if (direction.equalsIgnoreCase("down")) {
            return Optional.of(DOWN);
        } else if (direction.equalsIgnoreCase("up")) {
            return Optional.of(UP);
        } else {
            return Optional.empty();
        }
    }

}
