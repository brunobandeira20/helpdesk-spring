package com.bruno.helpdesk.domain.enums;

public enum Prioridade {
	
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	private Integer id;
	private String descricao;
	
	private Prioridade(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Prioridade toEnum(Integer cod) {
		if(cod == null)
			return null;
		for(Prioridade p: Prioridade.values()) {
			if(cod.equals(p.getId())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Prioridade Inválida");
	}
	

}
