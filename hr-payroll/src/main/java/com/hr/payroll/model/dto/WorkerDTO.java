package com.hr.payroll.model.dto;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WorkerDTO {

	@EqualsAndHashCode.Include
	private Long id;

	private String name;

	private BigDecimal dailyIncome;

}
