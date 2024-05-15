package com.driver.amazonWorkingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")

public class OrderController
{
    @Autowired
    OrderService orderService;

    @PostMapping("/add-order")

    public ResponseEntity<String> addOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
        return new ResponseEntity<>("new Order added succesfully", HttpStatus.CREATED);

    }

    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable String partnerId)
    {
        orderService.addPartner(partnerId);
        return new ResponseEntity<>("new delivery partner added succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId)
    {
         Order order=orderService.getOrderById(orderId);
         return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId)
    {
        DeliveryPartner deliveryPartner=orderService.getPartnerById(partnerId);
        return new ResponseEntity<>(deliveryPartner,HttpStatus.CREATED);
    }

    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId,@RequestParam String partnerId)
    {
        orderService.addOrderPartnerPair(orderId,partnerId);
        return new ResponseEntity<>("new order-partner pair added succesfully",HttpStatus.CREATED);

    }

    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId) {
        Integer orderCount = orderService.getOrderCountByPartnerId(partnerId);
        return new ResponseEntity<>(orderCount, HttpStatus.CREATED);
    }

    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<String>> getOrdersbyPartnerId(@PathVariable String partnerId)
    {
        List<String> orders = orderService.getOrdersbyPartnerId(partnerId);
        //orders should contains a list of orders by partner iD
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }
    @GetMapping("/get-all-orders")
    public ResponseEntity<List<String>> getAllOrders()
    {
        List<String> orders= orderService.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }
   @GetMapping("/get-count-ofunassigned-orders")
    public ResponseEntity<Integer>getCountOfUnassignedOrders()
    {
        Integer countOfOrders=orderService.getCountOfUnassignedOrders();
        return new ResponseEntity<>(countOfOrders,HttpStatus.CREATED);
    }
    @GetMapping("/get-orders-left-after-given-time-by-partner-id/{time}/{partnerId}")
    public ResponseEntity<Integer>getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time,@PathVariable String partnerId)
    {
        Integer countOfOrders=orderService.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
        return new ResponseEntity<>(countOfOrders,HttpStatus.CREATED);
    }
    @GetMapping("/get-last-delivery-time-by-partner-id/{partnerId}")
    public ResponseEntity<String> getLastDeliveryTimeBypartnerId(@PathVariable String partnerId)
    {
      String time=orderService.getLastDeliveryTimeBypartnerId(partnerId);
      return new ResponseEntity<>(time,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String>deletePartnerById(@PathVariable String partnerId)
    {
         orderService.deletePartnerById(partnerId);
        return new ResponseEntity<>(partnerId+" removed succesfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String>deleteOrderById(@PathVariable String orderId)
    {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(orderId,HttpStatus.CREATED);
    }


}
