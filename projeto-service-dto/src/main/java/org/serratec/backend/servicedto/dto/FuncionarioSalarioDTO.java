package org.serratec.backend.servicedto.dto; // Declaração do pacote onde a interface está localizada

// Interface FuncionarioSalarioDTO: Define um contrato para objetos que representam dados de funcionários com informações salariais
public interface FuncionarioSalarioDTO {

	// Método para obter a idade do funcionário
	public Integer getIdade();

	// Método para obter a média salarial dos funcionários
	public Double getMediaSalario();

	// Método para obter o menor salário entre os funcionários
	public Double getMenorSalario();

	// Método para obter o maior salário entre os funcionários
	public Double getMaiorSalario();

	// Método para obter o total de funcionários
	public Double getTotalFuncionarios();
}