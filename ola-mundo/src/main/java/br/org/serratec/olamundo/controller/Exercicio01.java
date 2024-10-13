package br.org.serratec.olamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calculadora")
public class Exercicio01 {


	@GetMapping("/soma")
	public Double soma(@RequestParam Double num1, @RequestParam Double num2) {
		double resultado = num1 + num2;
		return resultado;
	}
	
	@GetMapping("/subtracao")
	public Double subtracao(@RequestParam Double num1, @RequestParam Double num2) {
		double resultado = num1 - num2;
		return resultado;
	}
	
	@GetMapping("/multiplicacao")
	public Double multiplicacao(@RequestParam Double num1, @RequestParam Double num2) {
		double resultado = num1 * num2;
		return resultado;
	}
	
	@GetMapping("/divisao")
	public Double divisao(@RequestParam Double num1, @RequestParam Double num2) {
		double resultado = num1 / num2;
		return resultado;
	}
	
}