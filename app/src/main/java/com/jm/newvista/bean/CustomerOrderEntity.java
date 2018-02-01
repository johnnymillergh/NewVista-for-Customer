package com.jm.newvista.bean;

import org.litepal.crud.DataSupport;

import java.sql.Timestamp;

public class CustomerOrderEntity  extends DataSupport {
    private int id;
    private int userId;
    private Timestamp orderDatetime;
    private int movieScheduleId;
    private String movieTitle;
    private Timestamp showtime;
    private String seatId;
    private String seatLocation;
    private String auditoriumName;
    private String theaterName;
    private String theaterLocation;
    private boolean isPaid;
    private Timestamp paymentDatetime;
    private boolean isUsed;
    private Timestamp usedDatetime;
    private int ticketAmount;
    private float totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Timestamp orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public int getMovieScheduleId() {
        return movieScheduleId;
    }

    public void setMovieScheduleId(int movieScheduleId) {
        this.movieScheduleId = movieScheduleId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Timestamp getShowtime() {
        return showtime;
    }

    public void setShowtime(Timestamp showtime) {
        this.showtime = showtime;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(String seatLocation) {
        this.seatLocation = seatLocation;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterLocation() {
        return theaterLocation;
    }

    public void setTheaterLocation(String theaterLocation) {
        this.theaterLocation = theaterLocation;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Timestamp getPaymentDatetime() {
        return paymentDatetime;
    }

    public void setPaymentDatetime(Timestamp paymentDatetime) {
        this.paymentDatetime = paymentDatetime;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Timestamp getUsedDatetime() {
        return usedDatetime;
    }

    public void setUsedDatetime(Timestamp usedDatetime) {
        this.usedDatetime = usedDatetime;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CustomerOrderEntity: " + id + ", " + userId + ", " + orderDatetime + ", " + movieScheduleId;
    }
}
