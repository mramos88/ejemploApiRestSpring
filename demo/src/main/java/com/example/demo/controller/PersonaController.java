package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PersonaModel;

@RestController
@RequestMapping("/apiPersona")
public class PersonaController {
	List<PersonaModel> lista = new ArrayList<>();
	
	public PersonaController() {
		PersonaModel p1 = new PersonaModel("Matiasa","Ramos",321534);
		PersonaModel p2 = new PersonaModel("Jose","Perez",1234132);
		PersonaModel p3 = new PersonaModel("Gaston","Lopez",2456121);
		
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
		
	}

	@GetMapping("/message")
	public List<String> getMessage() {
		List<String> lista = new ArrayList<>();
		String msg = "Llegue al servidor";
		lista.add(msg);
		lista.add("Hola");
		lista.add("chau");
		System.out.println(msg);
		return lista;
		
	}
	@GetMapping({"/personas","/personas/{id}"})
	public ResponseEntity<?> getPersonas(@PathVariable(required = false) Long id) {
		if(id==null) {
			
			return new ResponseEntity<List<PersonaModel>>(this.lista,HttpStatus.OK);
		}else {
			try {
				
				return new ResponseEntity<PersonaModel>(this.lista.get(id.intValue()),HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@GetMapping("/persona/{dni}")
	public PersonaModel getPersona(@PathVariable Integer dni) {
		for(PersonaModel p :this.lista) {
			if(dni.equals(p.getDni())) {
				return p;
			}		
		}
		return null;
	}
	
	@PostMapping("/persona")
	public PersonaModel newPersona(@RequestBody PersonaModel persona) {
		
		this.lista.add(persona);
		return persona;
		
	}
	
	@PutMapping("/persona")
	public PersonaModel editPersona(@RequestBody PersonaModel persona) {
		
		for(PersonaModel p :this.lista) {
			if(persona.getDni().equals(p.getDni())) {
				p.setApellido(persona.getApellido());
				p.setNombre(persona.getNombre());
				return p;
			}		
		}
		return null;	
	}
	
	@DeleteMapping("/persona/{dni}")
	public boolean adsa(@PathVariable Integer dni){
		
		for(PersonaModel p :this.lista) {
			if(dni.equals(p.getDni())) {
				this.lista.remove(p);
				return true;
			}		
		}
		return false;
		
	}
	
	
}
