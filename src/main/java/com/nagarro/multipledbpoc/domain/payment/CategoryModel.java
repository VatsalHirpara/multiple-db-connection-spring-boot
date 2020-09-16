package com.nagarro.multipledbpoc.domain.payment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_models")
public class CategoryModel{
	
	@Id
	private long id;
	private long  category_partner_id;
	String model_cd;
	String variant_cd;
	long city_id;
	long tenure_id;
	String color_type;
	String unique_code;
}