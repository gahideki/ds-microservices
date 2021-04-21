package com.hr.payroll;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hr.payroll.model.Payment;
import com.hr.payroll.model.dto.WorkerDTO;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", workerId);

		WorkerDTO worker = restTemplate.getForObject(String.format("%s/workers/{id}", workerHost), WorkerDTO.class, uriVariables);
		Payment payment = new Payment(worker.getName(), worker.getDailyIncome(), days);
		return payment;
	}

}
