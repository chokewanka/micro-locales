package pe.com.chokewanka.springboot.micro.locales.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name="local")
@Where(clause = "is_deleted = 0")
public class Local implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="local_id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "local_x_ambiente", 
            joinColumns = { @JoinColumn(name = "local_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "ambiente_id") }
        )
	private List<Ambiente> ambientes = new ArrayList<Ambiente>();
	
	@Column(name="is_deleted")
	private Integer isDeleted;
	
	public Local() {}
	
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Ambiente> getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(List<Ambiente> ambientes) {
		this.ambientes = ambientes;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", isDeleted=" + isDeleted + "]";
	}

	private static final long serialVersionUID = -5148807826721807451L;

}
