package com.bruno.helpdesk.domain.enums;

public enum Status {
	
	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	private Integer id;
	private String descricao;
	
	private Status(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {
		if(cod == null)
			return null;
		for(Status p: Status.values()) {
			if(cod.equals(p.getId())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Status Inválido");
	}
	

}
