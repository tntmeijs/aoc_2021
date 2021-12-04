package dev.tahar.puzzle.day.four;

public final class BingoEntry {

    private final int number;

    private boolean marked;

    public BingoEntry(int number) {
        this.number = number;
        marked = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean getMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

}
