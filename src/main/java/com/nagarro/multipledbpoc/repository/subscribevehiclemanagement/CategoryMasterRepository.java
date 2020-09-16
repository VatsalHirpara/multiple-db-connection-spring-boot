package com.nagarro.multipledbpoc.repository.subscribevehiclemanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.domain.category.CategoryMaster;
@Repository
public interface CategoryMasterRepository extends JpaRepository<CategoryMaster, Long> {
	
	
	@Query(value = "Select cm.id from public.category_master cm Where  cm.name=:name", nativeQuery = true)
	Integer findIdByName(@Param("name") String name);
}
