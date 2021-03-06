package org.sid.web;
import org.sid.entities.*;

import java.util.List;

import org.sid.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller 

public class PatientController {
	@Autowired 
	private PatientRepository patientRepository ;
@GetMapping(path = "/index")
	public String index() {
		return "index";
	}

@GetMapping(path = "/patients")
public String list(Model model,
		@RequestParam(name="page",defaultValue="0")int page,
		@RequestParam(name="size",defaultValue="5")int size ,
        @RequestParam(name="keyword",defaultValue="")String mc ){
	Page<Patient> pagePatients = patientRepository.findByNomContains(mc,PageRequest.of(page, size));
	model.addAttribute("patients",pagePatients.getContent());
	model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
	model.addAttribute("currentPage", page);
	model.addAttribute("size", size);
	model.addAttribute("keyword",mc);
	return "patients";
} // Dans la suite on va chercher des patients
@GetMapping(path = "/deletePatient")
public String delete(Long id, String keyword, int page, int size) {
	patientRepository.deleteById(id);
	return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
}
@GetMapping(path="/formPatient")
public String formPatient(Model model) {
	model.addAttribute("patient", new Patient());
	return "formPatient";
}
@PostMapping(path="/savePatient")
public String savePatient(@Validated Patient patient,BindingResult bindingResult) {
	if(bindingResult.hasErrors()) return "formPatient";
	patientRepository.save(patient);
	return "formPatient";
	
}
}
  