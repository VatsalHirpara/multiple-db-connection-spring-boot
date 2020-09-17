package com.nagarro.multipledbpoc.repository.commonmasterdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.domain.commonmasterdata.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Query(value = "Select c.id from public.city c Where  c.name=:name", nativeQuery = true)
	Long findIdByName(@Param("name") String name);
}
