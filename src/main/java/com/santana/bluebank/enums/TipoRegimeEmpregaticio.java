package com.santana.bluebank.enums;

public enum TipoRegimeEmpregaticio {
	
	FORMAL("Formal"),
	INFORMAL("Informal"),
	ASSISTENCIAL("Limite Assistencial");
	
	private String description;
	
	private TipoRegimeEmpregaticio(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	

}
