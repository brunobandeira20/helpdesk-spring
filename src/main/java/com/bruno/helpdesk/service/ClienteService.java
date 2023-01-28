package com.bruno.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Pessoa;
import com.bruno.helpdesk.domain.Cliente;
import com.bruno.helpdesk.domain.DTO.ClienteDTO;
import com.bruno.helpdesk.repositories.PessoaRepository;
import com.bruno.helpdesk.repositories.ClienteRepository;
import com.bruno.helpdesk.service.exception.DataIntegrityViolationException;
import com.bruno.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = ClienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("OBJETO NÃO ENCONTRADO! ID: " + id));
	}

	public List<Cliente> findAll() {
		return ClienteRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaCpfEmail(objDTO);
		Cliente cliente = new Cliente(objDTO);
		return ClienteRepository.save(cliente);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaCpfEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return ClienteRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de servico e não pode ser deletado!");
		}
		ClienteRepository.deleteById(id);
	}

	private void validaCpfEmail(ClienteDTO objDTO) {
		List<Optional<Pessoa>> obj = pessoaRepository.findByCpfEmail(objDTO.getCpf(), objDTO.getEmail());
		for (Optional<Pessoa> pessoa : obj) {
			if (pessoa.isPresent() && objDTO.getCpf().contains(pessoa.get().getCpf())
					&& pessoa.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("CPF já cadastrado!");
			}
			if (pessoa.isPresent() && objDTO.getEmail().contains(pessoa.get().getEmail())
					&& pessoa.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("Email já cadastrado!");
			}
		}
	}

}
