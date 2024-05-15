package com.driver.amazonWorkingApp;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository
{
    Map<String,Order> OrdersDb = new HashMap<>();
    Map<String,DeliveryPartner> deliveryPartnerDb = new HashMap<>();
    Map<String,String> OrderPartnerDb= new HashMap<>();
    Map<String, List<String>> partnerOrdersDb = new HashMap<>();

    public void addOrder(Order order)
    {
        OrdersDb.put(order.getId(),order);
    }
    public void addPartner(String partnerId)
    {
        deliveryPartnerDb.put(partnerId,new DeliveryPartner(partnerId));
    }
    public Order getOrderById(String orderId)
    {
        return OrdersDb.get(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId)
    {
        return deliveryPartnerDb.get(partnerId);
    }
    public void addOrderPartnerPair(String orderId, String partnerId)
    {
        if(OrdersDb.containsKey(orderId) && deliveryPartnerDb.containsKey(partnerId))
        {
            OrderPartnerDb.put(orderId,partnerId);
            List<String> currentOrders=partnerOrdersDb.get(partnerId);
            if(partnerOrdersDb.containsKey(partnerId))
            {
                currentOrders=partnerOrdersDb.get(partnerId);
            }
            currentOrders.add(orderId);
            partnerOrdersDb.put(partnerId,currentOrders);
            //Increase the number of orders of partner
            DeliveryPartner deliveryPartner= deliveryPartnerDb.get(partnerId);
            deliveryPartner.setNumberOfOrders(currentOrders.size());
        }
    }
    public int getOrderCountByPartnerId(String partnerId)
    {
        return partnerOrdersDb.get(partnerId).size();
    }
    public List<String> getOrdersbyPartnerId(String partnerId)
    {
       return partnerOrdersDb.get(partnerId);
    }
    public List<String> getAllOrders()
    {
        List<String> orders = new ArrayList<>();
        for(String order:OrdersDb.keySet())
        {
            orders.add(order);
        }
        return orders;
    }
    public int getCountOfUnassignedOrders()
    {
        return OrdersDb.size()-OrderPartnerDb.size();
    }
    public  int  getOrdersLeftAfterGivenTimeByPartnerId(int time,String partnerId)
    {
        int count=0;
        List<String>orders=partnerOrdersDb.get(partnerId);
        for(String orderId:orders)
        {
            int deliveryTime=OrdersDb.get(orderId).getDeliveryTime();
            if(deliveryTime>time)
            {
                count++;
            }

        }
        return count;

    }
    public int getLastDeliveryTimeBypartnerId(String partnerId)
    {
        int maxTime=0;
        List<String>orders=partnerOrdersDb.get(partnerId);
        for(String orderId:orders)
        {
            int currentTime=OrdersDb.get(orderId).getDeliveryTime();
            maxTime=Math.max(maxTime,currentTime);
        }
        return maxTime;
    }
    public void deletePartnerById(String partnerId)
    {
        deliveryPartnerDb.remove(partnerId);
        List<String>ListOfOrders=partnerOrdersDb.get(partnerId);
        for(String order:ListOfOrders)
        {
            OrderPartnerDb.remove(order);
        }
    }
    public void deleteOrderById(String orderId)
    {
        String partnerId=OrderPartnerDb.get(orderId);
        OrderPartnerDb.remove(orderId);

        partnerOrdersDb.get(partnerId).remove(orderId);
        deliveryPartnerDb.get(partnerId).setNumberOfOrders(partnerOrdersDb.get(partnerId).size());
    }





}
