package com.nagarro.multipledbpoc.domain.vehiclepricing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class VehiclePricing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String City;

	private String NewOrUsed;

	private String CategoryName;

	private String CategoryCode;

	private String LeasingCompanyName;

	private String LeasingCompanyCode;

	private String CategoryLeasingConcode;

	private String ValidFrom;

	private String ValidTo;

	private String Model;

	private String Variant;
	
	private String Transmission;
	
	private String Color;
	
	private String Description;
	
	private String VariantCode;

	private String Tenor;

	private String UniqueCode;

	private Integer MonthlyRentalPreGST;

	private Integer MonthlyRentalGST;

	private Integer MonthlyRentalPostGST;

	private Integer DiscountPreGST;

	private Integer DiscountGST;

	private Integer DiscountPostGST;

	private Integer NetMonthlyRentalPreGST;

	private Integer NetMonthlyRentalGST;

	private Integer NetMonthlyRentalPostGST;

	private Integer SecurityDepositPreGST;

	private Integer SecurityDepositGST;

	private Integer SecurityDepositPostGST;

	private Integer ProcFeePreGST;

	private Integer ProcFeeGST;

	private Integer ProcFeePostGST;

	private Integer CustomerNeedstoPayPreGST;

	private Integer CustomerNeedstoPayGST;

	private Integer CustomerNeedstoPayPostGST;

}
