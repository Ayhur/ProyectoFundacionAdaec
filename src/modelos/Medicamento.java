package modelos;

public class Medicamento {

	protected int idMedicamento;
	protected String nombreMed;

	public Medicamento() {
	}
	
	public Medicamento(int id, String nombreMedicamento) {
		this.idMedicamento = id;
		this.nombreMed = nombreMedicamento;
	}

	public int getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(int id) {
		this.idMedicamento = id;
	}

	public String getNombreMed() {
		return nombreMed;
	}

	public void setNombreMed(String nombreMed) {
		this.nombreMed = nombreMed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMedicamento;
		result = prime * result + ((nombreMed == null) ? 0 : nombreMed.hashCode());
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
		Medicamento other = (Medicamento) obj;
		if (idMedicamento != other.idMedicamento)
			return false;
		if (nombreMed == null) {
			if (other.nombreMed != null)
				return false;
		} else if (!nombreMed.equals(other.nombreMed))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medicamento [idMedicamento=" + idMedicamento + ", nombreMed=" + nombreMed + "]";
	}

}
