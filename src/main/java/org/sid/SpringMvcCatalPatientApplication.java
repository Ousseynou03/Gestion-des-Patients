package org.sid;

import java.util.Date;
import java.util.List;

import org.sid.dao.PatientRepository;
import org.sid.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class SpringMvcCatalPatientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcCatalPatientApplication.class, args);
	}

	@Autowired
	private PatientRepository patientRepository;
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"Dione",new Date(),45,true));
		patientRepository.save(new Patient(null,"Ndiaye",new Date(),55,true));
		patientRepository.save(new Patient(null,"Diop",new Date(),65,true));
		patientRepository.save(new Patient(null,"Dia",new Date(),75,true));
		patientRepository.save(new Patient(null,"Coundoul",new Date(),85,true));
		patientRepository.save(new Patient(null,"Dieng",new Date(),65,true));
		patientRepository.save(new Patient(null,"Diagne",new Date(),65,true));
		System.out.println("-------------------------------------------------------------");
        System.out.println("Affichage de tous les patients");	
            
        Page<Patient> pagePatients=patientRepository.findAll(PageRequest.of(0, 2));
        System.out.println("Nombre de pages :"+pagePatients.getTotalPages());
        List<Patient> listPatients= pagePatients.getContent();
        listPatients.forEach(p->{
        	System.out.println(p.toString());
        });
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Chercher un patient sachant son identifiant");
        Patient p =patientRepository.findById(2L).get();
        System.out.println(p.getNom());
        System.out.println("Chercher tous les patients dont leur contient o");
        Page<Patient> patients =patientRepository.findByNomContains("o",PageRequest.of(0, 2));
        patients.forEach(e->{
        	System.out.println(e.toString());
        	
        });
        System.out.println("Chercher tous les patients qui sont malades");
        List<Patient> patients2 =patientRepository.findByMalade(true);
        patients.forEach(ep->{
        	System.out.println(ep.toString());
        	
        });
        System.out.println("Chercher tous les patients qui sont malades et dont le noùm contient o");
        List<Patient> patients3 =patientRepository.findByNomContainsAndMalade("o",true);
        patients.forEach(ep->{
        	System.out.println(ep.toString());
        	
        });
       // patientRepository.deleteById(2L);
        
		
		
	}

}






