package pe.com.chokewanka.springboot.micro.locales.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ambiente")
public class Ambiente implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ambiente_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ambientes")
	@JsonIgnore
	private List<Local> locales;
	
	public Ambiente() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Local> getLocales() {
		return locales;
	}

	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Ambiente that = (Ambiente) o;

		if (this.getId() != null ? !this.getId().equals(that.getId()) : that.getId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (this.getId() != null ? this.getId().hashCode() : 0);
	}

	@Override
	public String toString() {
		return "Ambiente [id=" + id + ", nombre=" + nombre + "]";
	}

	private static final long serialVersionUID = 6564494869503155826L;
	
}
