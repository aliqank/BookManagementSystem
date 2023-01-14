package com.bookmanagement.repository;

import com.bookmanagement.entity.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepository extends
        JpaRepository<Instance, Long> {
}
