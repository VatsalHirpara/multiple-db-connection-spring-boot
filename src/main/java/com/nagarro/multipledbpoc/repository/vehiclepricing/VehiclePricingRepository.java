package com.nagarro.multipledbpoc.repository.vehiclepricing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.domain.vehiclepricing.VehiclePricing;

@Repository
public interface VehiclePricingRepository extends JpaRepository<VehiclePricing, Long>{

}
