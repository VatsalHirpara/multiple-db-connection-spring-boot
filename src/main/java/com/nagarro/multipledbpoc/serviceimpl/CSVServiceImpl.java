package com.nagarro.multipledbpoc.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.multipledbpoc.domain.vehiclepricing.VehiclePricing;
import com.nagarro.multipledbpoc.repository.vehiclepricing.VehiclePricingRepository;
import com.nagarro.multipledbpoc.service.CSVService;
import com.nagarro.multipledbpoc.util.CSVUtil;

@Service
public class CSVServiceImpl implements CSVService {
	
	@Autowired
	VehiclePricingRepository vehiclePricingRepository;

	@Override
	@Transactional
	public void save(MultipartFile file) {
	    try {
	      List<VehiclePricing> vehiclePricings= CSVUtil.csvToVehiclePricings(file.getInputStream());
	      vehiclePricingRepository.saveAll(vehiclePricings);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }	
}
