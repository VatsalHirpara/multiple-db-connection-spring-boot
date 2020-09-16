package com.nagarro.multipledbpoc.domain.commonmasterdata;

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
@Table( name = "city")
public class City {
	@Id
	private long id;
	private String name;
	private String img_Url_1;
	private String img_url_2;
}
