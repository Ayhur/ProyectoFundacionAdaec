package modelos;

public class Tratamiento extends Medicamento {

	int id;
	boolean fueBien;

	public Tratamiento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isFueBien() {
		return fueBien;
	}

	public void setFueBien(boolean fueBien) {
		this.fueBien = fueBien;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (fueBien ? 1231 : 1237);
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tratamiento other = (Tratamiento) obj;
		if (fueBien != other.fueBien)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tratamiento [id=" + id + ", fueBien=" + fueBien + "]";
	}

}
