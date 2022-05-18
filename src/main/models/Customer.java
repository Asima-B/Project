package main.models;

import java.util.ArrayList;

public class Customer<T> extends User {

    private T id;
    private String name;
    private ArrayList<Booking> myBooking;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Customer(String password, String email, String name, ArrayList<Booking> myBooking) {
        super(password, email);
        this.name = name;
        this.myBooking = myBooking;
    }


    public Customer(String name,String email, String password) {
        super(password, email);
        this.name = name;
        this.myBooking = new ArrayList<>();
    }


    @Override
    public String getInfo() {

        return this.name+","+this.getEmail()+","+this.getPassword()+"/n"+this.myBookings();
    }

    public ArrayList<Booking> getMyBooking() {
        return myBooking;
    }

    public String myBookings() {
        StringBuilder res = new StringBuilder();
        if(this.getMyBooking().isEmpty()){
            return " ";

        }
        else {
            for (Booking b: getMyBooking()){
                res.append(b.getBookingId()+"-");
            }
        }
        return res.toString();
    }

    public void setMyBooking(ArrayList<Booking> myBooking) {
        this.myBooking = myBooking;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
