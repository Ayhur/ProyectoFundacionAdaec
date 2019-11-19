package modelos;

public class PreguntaRespuestasMaternidad {

	// Atributos preguntas
	private int idpregunta;
	private String descripcion;
	private int tipo;
	private int nivel;
	private int orden;
	private boolean cajaAdicional;
	private int minSlider;
	private int maxSlider;
	private int bloque;

	// Atributos respuestas
	private Integer idRespuesta;
	private String descripcionResp;

	public PreguntaRespuestasMaternidad() {
	}

	public int getIdpregunta() {
		return idpregunta;
	}

	public void setIdpregunta(int idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public boolean isCajaAdicional() {
		return cajaAdicional;
	}

	public void setCajaAdicional(boolean cajaAdicional) {
		this.cajaAdicional = cajaAdicional;
	}

	public int getMinSlider() {
		return minSlider;
	}

	public void setMinSlider(int minSlider) {
		this.minSlider = minSlider;
	}

	public int getMaxSlider() {
		return maxSlider;
	}

	public void setMaxSlider(int maxSlider) {
		this.maxSlider = maxSlider;
	}

	public int getBloque() {
		return bloque;
	}

	public void setBloque(int bloque) {
		this.bloque = bloque;
	}

	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getDescripcionResp() {
		return descripcionResp;
	}

	public void setDescripcionResp(String descripcionResp) {
		this.descripcionResp = descripcionResp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bloque;
		result = prime * result + (cajaAdicional ? 1231 : 1237);
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((descripcionResp == null) ? 0 : descripcionResp.hashCode());
		result = prime * result + ((idRespuesta == null) ? 0 : idRespuesta.hashCode());
		result = prime * result + idpregunta;
		result = prime * result + maxSlider;
		result = prime * result + minSlider;
		result = prime * result + nivel;
		result = prime * result + orden;
		result = prime * result + tipo;
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
		PreguntaRespuestasMaternidad other = (PreguntaRespuestasMaternidad) obj;
		if (bloque != other.bloque)
			return false;
		if (cajaAdicional != other.cajaAdicional)
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (descripcionResp == null) {
			if (other.descripcionResp != null)
				return false;
		} else if (!descripcionResp.equals(other.descripcionResp))
			return false;
		if (idRespuesta == null) {
			if (other.idRespuesta != null)
				return false;
		} else if (!idRespuesta.equals(other.idRespuesta))
			return false;
		if (idpregunta != other.idpregunta)
			return false;
		if (maxSlider != other.maxSlider)
			return false;
		if (minSlider != other.minSlider)
			return false;
		if (nivel != other.nivel)
			return false;
		if (orden != other.orden)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PreguntaRespuestasMaternidad [idpregunta=" + idpregunta + ", descripcion=" + descripcion + ", tipo="
				+ tipo + ", nivel=" + nivel + ", orden=" + orden + ", cajaAdicional=" + cajaAdicional + ", minSlider="
				+ minSlider + ", maxSlider=" + maxSlider + ", bloque=" + bloque + ", idRespuesta=" + idRespuesta
				+ ", descripcionResp=" + descripcionResp + "]";
	}

}
