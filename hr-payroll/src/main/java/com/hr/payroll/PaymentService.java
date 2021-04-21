package com.hr.payroll;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.hr.payroll.model.Payment;

@Service
public class PaymentService {

	public Payment getPayment(Long workerId, Integer days) {
		Payment payment = new Payment("Gabriel", BigDecimal.TEN, days);
		return payment;
	}

}
