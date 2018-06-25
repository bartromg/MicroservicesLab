package com.pyshankov.microservices.statisticservice.repository;

import com.pyshankov.microservices.statisticservice.domain.ProductEvent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by valeriyartemenko on 06.05.18.
 */
public interface StatisticRepository extends CrudRepository<ProductEvent, String> {
//
//    @Query(value="SELECT * FROM event WHERE firstname=?0")
//    public List<Event> findByFirstname(String firstname);

}