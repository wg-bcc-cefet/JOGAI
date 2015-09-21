package br.cefetrj.jogai.dominio;

/**Enumerador dos tipos possíveis de pino
 * 
 * @author JOGAI
 *
 */
public enum EnumPino {
	BRANCO_4_FUROS("Branco 4 Furos"), BRANCO_3_FUROS("Branco 3 Furos"), BRANCO_1_FURO(
			"Branco 1 Furo"), BRANCO_MEIA_RODA("Branco Meia-Roda"), AMARELO_4_FUROS(
			"Amarelo 4 Furos"), AMARELO_3_FUROS("Amarelo 3 Furos"), AMARELO_1_FURO(
			"Amarelo 1 Furo"), AMARELO_MEIA_RODA("Amarelo Meia-Roda"), VERMELHO_4_FUROS(
			"Vermelho 4 Furos"), VERMELHO_3_FUROS("Vermelho 3 Furos"), VERMELHO_1_FURO(
			"Vermelho 1 Furo"), VERMELHO_MEIA_RODA("Vermelho Meia-Roda"), AZUL_4_FUROS(
			"Azul 4 Furos"), AZUL_3_FUROS("Azul 3 Furos"), AZUL_1_FURO(
			"Azul 1 Furo"), AZUL_MEIA_RODA("Azul Meia-Roda"), VERDE_4_FUROS(
			"Verde 4 Furos"), VERDE_3_FUROS("Verde 3 Furos"), VERDE_1_FURO(
			"Verde 1 Furo");

	private String pino;

	/**
	 * Construtor para que o enumerador fique "apresentável"
	 * @param s
	 */
	private EnumPino(String s) {

		pino = s;

	}

	public String getPino() {

		return pino;

	}
}
