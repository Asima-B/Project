package main.models;

public class Film {
    private String title = "Default Title";
    private String description = "Default Description";

    private String    startDate = "yyyy-mm-dd";
    private String endDate = "yyyy-mm-dd";
    private String[] times = {"hh:mm", "hh:mm", "hh:mm"};
    private Double price;

    public Film(String title, String description, String startDate, String endDate, String[] times, Double price) {
        this.price = price;


        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.times = times;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTimes() {
        String res="";
        for (String t:times){
            res+=t+",";
        }
        return res;


    }

    public String[] getTimesInArray() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
