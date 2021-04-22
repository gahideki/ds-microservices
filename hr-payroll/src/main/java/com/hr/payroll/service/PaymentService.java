package com.hr.payroll.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.payroll.feignclient.WorkerFeignClient;
import com.hr.payroll.model.Payment;
import com.hr.payroll.model.dto.WorkerDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	@HystrixCommand(fallbackMethod = "getPaymentAlternative")
	public Payment getPayment(Long workerId, Integer days) {
		WorkerDTO worker = workerFeignClient.getWorker(workerId).getBody();
		Payment payment = new Payment(worker.getName(), worker.getDailyIncome(), days);
		return payment;
	}

	public Payment getPaymentAlternative(Long workerId, Integer days) {
		Payment payment = new Payment("Brann", BigDecimal.ZERO, days);
		return payment;
	}

}
