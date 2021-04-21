package com.hr.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.payroll.feignclient.WorkerFeignClient;
import com.hr.payroll.model.Payment;
import com.hr.payroll.model.dto.WorkerDTO;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(Long workerId, Integer days) {
		WorkerDTO worker = workerFeignClient.getWorker(workerId).getBody();
		Payment payment = new Payment(worker.getName(), worker.getDailyIncome(), days);
		return payment;
	}

}
