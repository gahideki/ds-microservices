package com.hr.worker.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.worker.model.Worker;
import com.hr.worker.service.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {

	@Autowired
	private WorkerService service;

	@GetMapping
	public ResponseEntity<List<Worker>> getWorkers() {
		List<Worker> workers = service.getWorkers();

		if (workers.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(workers);
	}

	@GetMapping("/{workerId}")
	public ResponseEntity<Worker> getWorker(@PathVariable Long workerId) {
		Worker worker = service.getWorker(workerId);
		return ResponseEntity.ok(worker);
	}

}
