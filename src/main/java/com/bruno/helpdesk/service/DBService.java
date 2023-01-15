package com.bruno.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.helpdesk.domain.Chamado;
import com.bruno.helpdesk.domain.Cliente;
import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.domain.enums.Perfil;
import com.bruno.helpdesk.domain.enums.Prioridade;
import com.bruno.helpdesk.domain.enums.Status;
import com.bruno.helpdesk.repositories.ChamadoRepository;
import com.bruno.helpdesk.repositories.ClienteRepository;
import com.bruno.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instaciaDB() {
		Tecnico tec1 = new Tecnico(null, "Bruno Bandeira", "12345678952", "brunobandeira20@gmail.com", "123");
		tec1.addtPerfis(Perfil.ADMIN);
		
		Cliente cliente = new Cliente(null, "Maria", "12343213122", "maria@gmail.com", "asd");
		
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "TESTE", "Nenhuma", tec1, cliente);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cliente));
		chamadoRepository.saveAll(Arrays.asList(cha1));
	}
}
