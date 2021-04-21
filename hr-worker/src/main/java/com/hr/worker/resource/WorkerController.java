package com.hr.worker.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

	private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

	@Autowired
	private Environment env;

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
		logger.info("Port = " + env.getProperty("local.server.port"));

		Worker worker = service.getWorker(workerId);
		return ResponseEntity.ok(worker);
	}

}
