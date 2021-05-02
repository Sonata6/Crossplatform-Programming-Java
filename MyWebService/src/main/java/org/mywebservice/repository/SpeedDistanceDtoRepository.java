package org.mywebservice.repository;


import org.mywebservice.entity.SpeedDistanceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedDistanceDtoRepository extends JpaRepository<SpeedDistanceDto, Long> {
}