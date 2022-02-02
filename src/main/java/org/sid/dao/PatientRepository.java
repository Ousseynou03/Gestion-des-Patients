package org.sid.dao;

import java.util.List;

import org.sid.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	public Page<Patient> findByNomContains(String mc, Pageable pageable);
	public List<Patient> findByMalade(boolean b);
	public List<Patient> findByNomContainsAndMalade(String name,boolean b);
}
