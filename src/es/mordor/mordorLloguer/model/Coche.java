package es.mordor.mordorLloguer.model;

import java.sql.Date;

public class Coche extends Vehicle {

	protected int numPlazas;
	protected int numPuertas;
	
	public Coche(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet, int numPlazas, int numPuertas) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaAdq, estado, carnet);
		this.numPlazas = numPlazas;
		this.numPuertas = numPuertas;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public int getNumPuertas() {
		return numPuertas;
	}

	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	@Override
	public String toString() {
		return "Coche [numPlazas=" + numPlazas + ", numPuertas=" + numPuertas + ", getMatricula()=" + getMatricula()
				+ ", getPrecioDia()=" + getPrecioDia() + ", getMarca()=" + getMarca() + ", getDescripcion()="
				+ getDescripcion() + ", getColor()=" + getColor() + ", getMotor()=" + getMotor() + ", getCilindrada()="
				+ getCilindrada() + ", getFechaAdq()=" + getFechaAdq() + ", getEstado()=" + getEstado()
				+ ", getCarnet()=" + getCarnet() + "]";
	}
		
}
