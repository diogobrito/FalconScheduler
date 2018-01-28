package com.falconScheduler.multitenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.falconScheduler.multitenant.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
