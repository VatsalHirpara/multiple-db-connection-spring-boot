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

	private long Tenor;

	private String UniqueCode;

	private int MonthlyRentalPreGST;

	private int MonthlyRentalGST;

	private int MonthlyRentalPostGST;

	private int DiscountPreGST;

	private int DiscountGST;

	private int DiscountPostGST;

	private int NetMonthlyRentalPreGST;

	private int NetMonthlyRentalGST;

	private int NetMonthlyRentalPostGST;

	private int SecurityDepositPreGST;

	private int SecurityDepositGST;

	private int SecurityDepositPostGST;

	private int ProcFeePreGST;

	private int ProcFeeGST;

	private int ProcFeePostGST;

	private int CustomerNeedstoPayPreGST;

	private int CustomerNeedstoPayGST;

	private int CustomerNeedstoPayPostGST;

}
