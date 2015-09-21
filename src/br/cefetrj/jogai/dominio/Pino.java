package br.cefetrj.jogai.dominio;

import java.math.BigDecimal;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe Pino - Representa os pinos utilizados durante o jogo
 * Cada pino possui um enumerável de tipo do pino, e um intervalo mínimo-máximo de preço.
 * @author JOGAI
 *
 */
//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
@DiscriminatorValue ( "PI" )
public class Pino extends Vendavel {

	//private EnumForma forma;

	//private EnumCor cor;

	private EnumPino tipo;
	
	private BigDecimal precoMin;
	
	private BigDecimal precoMax;

	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public Pino() {
		// TODO Auto-generated constructor stub
	}
	
	public Pino(EnumPino tipo, BigDecimal min, BigDecimal max){
		
		this.tipo = tipo;
		this.precoMin = min;
		this.precoMax = max;
		
	}

	public EnumPino getTipo() {
		return tipo;
	}

	public void setTipo(EnumPino tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPrecoMin() {
		return precoMin;
	}

	public void setPrecoMin(BigDecimal precoMin) {
		this.precoMin = precoMin;
	}

	public BigDecimal getPrecoMax() {
		return precoMax;
	}

	public void setPrecoMax(BigDecimal precoMax) {
		this.precoMax = precoMax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pino other = (Pino) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	

}
