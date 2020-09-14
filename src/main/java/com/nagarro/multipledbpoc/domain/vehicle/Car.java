package com.nagarro.multipledbpoc.domain.vehicle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String modelName;
	private String color;
}
