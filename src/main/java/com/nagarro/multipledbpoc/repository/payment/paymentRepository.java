package com.nagarro.multipledbpoc.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.domain.payment.CategoryModel;

@Repository
public interface paymentRepository extends JpaRepository<CategoryModel, Long> {

	@Query(value = "Select cm.id from public.category_models AS cm WHERE (cm.category_partner_id=:category_partner_id AND cm.model_cd=:model_cd AND  cm.variant_cd=:variant_cd AND  cm.city_id=:city_id AND cm.tenure_id=:tenure_id AND cm.color_type=:color_type)", nativeQuery = true)
	Integer findId(@Param("category_partner_id") long category_partner_id, @Param("model_cd") String model_cd,
			@Param("variant_cd") String variant_cd, @Param("city_id") long city_id, @Param("tenure_id") long tenure_id,
			@Param("color_type") String color_type);
}