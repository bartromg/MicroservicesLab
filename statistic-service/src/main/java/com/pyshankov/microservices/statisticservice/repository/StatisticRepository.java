package com.pyshankov.microservices.statisticservice.repository;

import com.pyshankov.microservices.domain.Event;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by valeriyartemenko on 06.05.18.
 */
public interface StatisticRepository extends CrudRepository<Event, String> {

    @Query(value="SELECT * FROM event WHERE firstname=?0")
    public List<Event> findByFirstname(String firstname);

}