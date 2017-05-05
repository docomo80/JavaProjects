package com.motorcycle.areas.motorcycle.repositories;

import com.motorcycle.areas.motorcycle.entities.Motorcycle;
import com.motorcycle.areas.motorcycle.models.viewModels.MotorcycleInfoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {

    Motorcycle findFirstByMake(String make);

    @Query("select  m  from Motorcycle as m")
    List<Motorcycle> getAll();
}
