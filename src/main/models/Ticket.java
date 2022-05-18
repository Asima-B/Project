package main.models;

public class Ticket {
    private int id;
    private String userName;
    private String filmTitle;
    private String filmTime;
    private String bookedTime;
    private double price;
    private String seat;


    public Ticket(int id, String userName, String filmTitle, String filmTime, String bookedTime, double price, String seat) {
        this.id = id;
        this.userName = userName;
        this.filmTitle = filmTitle;
        this.filmTime = filmTime;
        this.bookedTime = bookedTime;
        this.price = price;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(String filmTime) {
        this.filmTime = filmTime;
    }

    public String getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(String bookedTime) {
        this.bookedTime = bookedTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
