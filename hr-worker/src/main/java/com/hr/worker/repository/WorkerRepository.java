package com.hr.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.worker.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
