package com.jm.newvista.bean;

import org.litepal.crud.DataSupport;

import java.sql.Timestamp;

public class OrderEntity extends DataSupport {
    private int id;
    private int userId;
    private Timestamp orderDatetime;
    private int movieScheduleId;
    private boolean isPaid;
    private boolean idUsed;
    private int ticketAmount;
    private int totalPrice;

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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isIdUsed() {
        return idUsed;
    }

    public void setIdUsed(boolean idUsed) {
        this.idUsed = idUsed;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderEntity: " + id + ", " + userId + ", " + orderDatetime + ", " + movieScheduleId;
    }
}
