package com.nagarro.multipledbpoc.domain.category;

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
@Table(name = "category_master")
public class CategoryMaster {
	@Id
	private long id;
	private String name;
}
