package com.hr.worker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.worker.model.Worker;
import com.hr.worker.repository.WorkerRepository;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repository;

	public List<Worker> getWorkers() {
		return repository.findAll();
	}

	public Worker getWorker(Long workerId) {
		return repository.findById(workerId).orElseThrow(() -> new RuntimeException("Worker not found"));
	}

}
