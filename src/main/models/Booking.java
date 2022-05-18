package main.models;

import static main.Main.bookings;

public class Booking implements BookingInfo{
    private int bookingId;
    private String userEmail;
    private String filmTitle;
    private String date;
    private String time;
    private Seat seat;
    private Double totalPrice;


    public Booking(String userEmail, String filmTitle, String date, String time, Seat seat, Double totalPrice) {
        this.bookingId = newBookingID();
        this.userEmail = userEmail;
        this.filmTitle = filmTitle;
        this.date = date;
        this.time = time;
        this.seat = seat;
        this.totalPrice = totalPrice;
    }

    static int newBookingID() {
        if (bookings.isEmpty()) {
            return 1;
        } else {
            int index = bookings.get(bookings.size() - 1).getBookingId() + 1;
            return index;
        }
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }



    @Override
    public void bookingInfo() {
        System.out.println(bookingId+" "+this.getUserEmail()+" "+this.getTotalPrice()+ " " +this.getSeat());
    }
}
