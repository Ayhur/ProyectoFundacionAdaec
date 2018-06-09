package modelos;

import java.util.List;

public class BarrasDiagrama {

	String etiquetasBarra;
	int valorEetiquetasBarra;
	String valorColorFondoBarraYContorno;
	double solidedColorFondoBarra, solidedColorBorderBarra;
	/*
	 * final static double solidedColorFondoBarra = 0.2; final static double
	 * solidedColorBorderBarra = 1.0;
	 */

	public BarrasDiagrama() {
	}

	public double getSolidedColorFondoBarra() {
		return solidedColorFondoBarra;
	}

	public void setSolidedColorFondoBarra(double solidedColorFondoBarra) {
		this.solidedColorFondoBarra = solidedColorFondoBarra;
	}

	public double getSolidedColorBorderBarra() {
		return solidedColorBorderBarra;
	}

	public void setSolidedColorBorderBarra(double solidedColorBorderBarra) {
		this.solidedColorBorderBarra = solidedColorBorderBarra;
	}

	public String getEtiquetasBarra() {
		return etiquetasBarra;
	}

	public void setEtiquetasBarra(String etiquetasBarra) {
		this.etiquetasBarra = etiquetasBarra;
	}

	public int getValorEetiquetasBarra() {
		return valorEetiquetasBarra;
	}

	public void setValorEetiquetasBarra(int valorEetiquetasBarra) {
		this.valorEetiquetasBarra = valorEetiquetasBarra;
	}

	public String getValorColorFondoBarraYContorno() {
		return valorColorFondoBarraYContorno;
	}

	public void setValorColorFondoBarraYContorno(String valorColorFondoBarraYContorno) {
		this.valorColorFondoBarraYContorno = valorColorFondoBarraYContorno;
	}

}
