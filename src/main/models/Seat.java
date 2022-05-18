package main.models;

public class Seat {
    private int row;
    private int column;
    private boolean booked = false;

    public Seat(int row, int column, boolean booked) {
       this.row=row;
            this.column = column;
            this.booked = booked;

    }

    public Seat(char row, int column) {
        this (row, column, false);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getLabel() {
        return row + String.valueOf(column);
    }

    public boolean getBookingStatus() {
        return booked;
    }

    public void setBookingStatus(boolean booked) {
        this.booked = booked;
    }

    public String toString(){
        return this.getRow()+"-"+this.getColumn();
    }
}
