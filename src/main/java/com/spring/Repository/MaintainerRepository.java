package com.spring.Repository;

import com.spring.Entity.Maintainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintainerRepository extends JpaRepository<Maintainer, Long> {
}
