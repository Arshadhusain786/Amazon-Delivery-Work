package com.driver.amazonWorkingApp;

import org.jetbrains.annotations.NotNull;

public class Order
{
    private String id;

    private int deliveryTime;

    public Order(String id,  String deliveryTime)
    {
     // deliveryTime has to converted from String to int and then strored in
        // the attribute
        // delivery time= HH * 60 + MM
        this.id=id;
        String time[]= deliveryTime.split(":");
        this.deliveryTime = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        id = id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
