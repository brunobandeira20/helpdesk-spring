package com.bruno.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.domain.DTO.TecnicoDTO;
import com.bruno.helpdesk.repositories.TecnicoRepository;
import com.bruno.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("OBJETO NÃO ENCONTRADO! ID: " + id));
	}
	
	public List<Tecnico> findAll(){
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		Tecnico tecnico = new Tecnico(objDTO);
		return tecnicoRepository.save(tecnico);
	}
}
