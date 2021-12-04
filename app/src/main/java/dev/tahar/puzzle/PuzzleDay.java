package dev.tahar.puzzle;

/**
 * Indicates the day of a puzzle - this number is used to determine which puzzle input source to load from disk
 * <p>
 * Please note that the order of the enum's values matter <i>a lot</i>. The order in which the values are declared, is
 * the order in which they will be mapped to the 1st of December until the 25th of December.
 * </p>
 * <p>
 * If you swap, for example, {@link PuzzleDay#EIGHT} with {@link PuzzleDay#SEVEN}, the 8th of December will suddenly use
 * the puzzle input of the 7th of December. All this is pretty obvious, but it is good to be aware of it nonetheless.
 * </p>
 */
public enum PuzzleDay {

    /**
     * First of December 2021
     */
    ONE,

    /**
     * Second of December 2021
     */
    TWO,

    /**
     * Third of December 2021
     */
    THREE,

    /**
     * Fourth of December 2021
     */
    FOUR,

    /**
     * Fifth of December 2021
     */
    FIVE,

    /**
     * Sixth of December 2021
     */
    SIX,

    /**
     * Seventh of December 2021
     */
    SEVEN,

    /**
     * Eighth of December 2021
     */
    EIGHT,

    /**
     * Ninth of December 2021
     */
    NINE,

    /**
     * Tenth of December 2021
     */
    TEN,

    /**
     * Eleventh of December 2021
     */
    ELEVEN,

    /**
     * Twelfth of December 2021
     */
    TWELVE,

    /**
     * Thirteenth of December 2021
     */
    THIRTEEN,

    /**
     * Fourteenth of December 2021
     */
    FOURTEEN,

    /**
     * Fifteenth of December 2021
     */
    FIFTEEN,

    /**
     * Sixteenth of December 2021
     */
    SIXTEEN,

    /**
     * Seventeenth of December 2021
     */
    SEVENTEEN,

    /**
     * Eighteenth of December 2021
     */
    EIGHTEEN,

    /**
     * Nineteenth of December 2021
     */
    NINETEEN,

    /**
     * Twentieth of December 2021
     */
    TWENTY,

    /**
     * Twenty-first of December 2021
     */
    TWENTY_ONE,

    /**
     * Twenty-second of December 2021
     */
    TWENTY_TWO,

    /**
     * Twenty-third of December 2021
     */
    TWENTY_THREE,

    /**
     * Twenty-fourth of December 2021
     */
    TWENTY_FOUR,

    /**
     * Twenty-fifth of December 2021
     */
    TWENTY_FIVE

}
