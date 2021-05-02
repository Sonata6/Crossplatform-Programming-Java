package org.mywebservice.repository;

import org.mywebservice.entity.TimeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDtoRepository extends JpaRepository<TimeDto, Long> {
}
