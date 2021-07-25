package com.srdu001.sswf.bd;

import org.springframework.data.repository.CrudRepository;

public interface IForecastDAO extends CrudRepository<Forecast, Integer> {

    Forecast findByDay(Integer day);

}
