package com.upgrad.orderservice.controller;

import com.upgrad.commons.model.Status;
import com.upgrad.commons.model.request.OrderRequest;
import com.upgrad.commons.model.response.OrderResponse;
import com.upgrad.orderservice.model.OrderException;
import com.upgrad.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public final class OrderController {


  private final OrderService orderService;
  private static final Logger LOG= LoggerFactory.getLogger(OrderController.class);

  @PostMapping("/place-order")
  public OrderResponse placeOrder(@RequestBody final OrderRequest orderRequest) {
    LOG.info("testing");
    try {
      LOG.info("Order placed item:{} by user:{}", orderRequest.getItemId(),orderRequest.getUser().getId());
      return OrderResponse.builder()
          .orderId(orderService.placeOrder(orderRequest.getUser(), orderRequest.getItemId()))
          .itemId(orderRequest.getItemId())
          .orderStatus(Status.SUCCESSFUL)
          .build();
    } catch (final OrderException e) {
      LOG.error("Exception occurring while placing order item:{} by user:{}",orderRequest.getItemId(),orderRequest.getUser().getId(), e);
      return OrderResponse.builder()
          .itemId(orderRequest.getItemId())
          .orderStatus(Status.FAILED)
          .build();
    }
  }
  @GetMapping(value = "/live-status")
   public ResponseEntity appStatus(){
    return ResponseEntity.ok().body("Your Application is running");
   }
}
