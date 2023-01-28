package com.bruno.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Pessoa;
import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.domain.DTO.TecnicoDTO;
import com.bruno.helpdesk.repositories.PessoaRepository;
import com.bruno.helpdesk.repositories.TecnicoRepository;
import com.bruno.helpdesk.service.exception.DataIntegrityViolationException;
import com.bruno.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("OBJETO NÃO ENCONTRADO! ID: " + id));
	}
	
	public List<Tecnico> findAll(){
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaCpfEmail(objDTO);
		Tecnico tecnico = new Tecnico(objDTO);
		return tecnicoRepository.save(tecnico);
	}

	private void validaCpfEmail(TecnicoDTO objDTO) {
		List<Optional<Pessoa>> obj = pessoaRepository.findByCpfEmail(objDTO.getCpf(), objDTO.getEmail());
		for(Optional<Pessoa> pessoa : obj) {
			if(pessoa.isPresent() && objDTO.getCpf().contains(pessoa.get().getCpf()) && pessoa.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("CPF já cadastrado!");
			}
			if(pessoa.isPresent() && objDTO.getEmail().contains(pessoa.get().getEmail()) && pessoa.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("Email já cadastrado!");
			}
		}
	}
}
