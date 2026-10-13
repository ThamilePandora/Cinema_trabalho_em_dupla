package com.podora.cinema;

public class Sessao {
	private final String codigoIdentificador;
	private final Filme filme;
	private final int horaInicio; 
	private int capacidadeMaxima;
	private int qtdIngressos;
	
	private float valorArrecadado;
	private float valor;
	private static float precoIntegral = 20;
	
	//Criar Sessao
	public Sessao (String codigoIdentificador, Filme filme, int horaInicio, int capacidadeMaxima){
		this.codigoIdentificador = codigoIdentificador;
        this.filme = filme;
        this.horaInicio = horaInicio;
		if(capacidadeMaxima <0) {
			this.capacidadeMaxima = 50;
		}
		this.qtdIngressos = 0;
        this.valorArrecadado = 0;
	}
	
	public Sessao (String codigoIdentificador,Filme filme, int horaInicio){
		this.codigoIdentificador = codigoIdentificador;
        this.filme = filme;
        this.horaInicio = horaInicio;
		capacidadeMaxima = 50;
	}
	public float getPrecoIntegral() {
		return precoIntegral;
	}

	public boolean setPrecoIntegral(float novoValor) {
		if(precoIntegral>0) {
			precoIntegral = novoValor;
			return true;
		}
		return false;
	}
	
	public int getQtdIngressos() {
		return qtdIngressos;
	}


	public float getValorArrecadado() {
		return valorArrecadado;
	}

	public String getCodigoIdentificador() {
		return codigoIdentificador;
	}

	public Filme getFilme() {
		return filme;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public int getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
	public int lugaresDisponiveis() {
		return capacidadeMaxima - qtdIngressos;
	}
	private static float valorPercentual(float percentual) {
		return precoIntegral * (1 - percentual/ 100);
	}
	
	public float venderIngresso(int idadeEspectador) {
		if(idadeEspectador<0){
			return -1;
		}
		if(idadeEspectador < filme.getClassificacaoEtaria()){
			return -2;
		}
		if(lugaresDisponiveis() <= 0){
			return -3;
		}
		valor = valorPercentual(0);
		qtdIngressos = qtdIngressos + 1;
		
		valorArrecadado += valor;
		return valorArrecadado;
	}
	
	public double venderIngresso(int idadeEspectador, String nDocumento) {
		if((idadeEspectador<0 || nDocumento == null || nDocumento.isBlank() == true)){
			return -1;
		}
		if(idadeEspectador < filme.getClassificacaoEtaria()){
			return -2;
		}
		if(lugaresDisponiveis() <= 0){
			return -3;
		}
		valor = valorPercentual(50);
		qtdIngressos = qtdIngressos + 1;
		
		valorArrecadado += valor;
		return valorArrecadado;
	}
	// Retorna o horário fim da sessao
    public int getHoraTermino() {
        return horaInicio + filme.getDuracao();
    }
	
}
