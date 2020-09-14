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

	private String MonthlyRentalPreGST;

	private String MonthlyRentalGST;

	private String MonthlyRentalPostGST;

	private String DiscountPreGST;

	private String DiscountGST;

	private String DiscountPostGST;

	private String NetMonthlyRentalPreGST;

	private String NetMonthlyRentalGST;

	private String NetMonthlyRentalPostGST;

	private String SecurityDepositPreGST;

	private String SecurityDepositGST;

	private String SecurityDepositPostGST;

	private String ProcFeePreGST;

	private String ProcFeeGST;

	private String ProcFeePostGST;

	private String CustomerNeedstoPayPreGST;

	private String CustomerNeedstoPayGST;

	private String CustomerNeedstoPayPostGST;

}
