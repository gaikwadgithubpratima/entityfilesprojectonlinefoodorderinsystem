package com.sb.foodsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.foodsystem.converter.PaymentConverter;
import com.sb.foodsystem.model.PaymentDTO;
import com.sb.foodsystem.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @SuppressWarnings("unused")
	private PaymentConverter paymentConverter;
    
    public PaymentController(PaymentService paymentService, PaymentConverter paymentConverter) {
		super();
		this.paymentService = paymentService;
		this.paymentConverter = paymentConverter;
	}

	//Read (Retrieve) all payments
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments(@PathVariable Long paymentId) {
        List<PaymentDTO> payments = paymentService.getAllPayments(paymentId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    //Read (Retrieve) a payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long paymentId) {
        PaymentDTO payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Create (Add) a new payment
    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO createPayment = paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(createPayment, HttpStatus.CREATED);
    }

    //Update (Edit) an existing payment
    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentDTO updatedPayment) {
        PaymentDTO updated = paymentService.updatePayment(paymentId, updatedPayment);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Delete a payment by ID
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}


