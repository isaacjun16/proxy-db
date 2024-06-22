package gt.edu.umg.dbproxy.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductoDto {
	
	@NotEmpty(message = "El nombre es requerido.")
	private String nombre;
	
	@NotEmpty(message = "La marca es requerida.")
	private String marca;
	
	@NotEmpty(message = "La categoria es requerida.")
	private String categoria;
	
	@Min(0)
	private Double precio;
	
	@Size(min = 10, message = "La descripción tiene que tener por lo menos 10 caracteres")
	@Size(max = 2000, message = "La descripción no puede tener mas de 2000 caracteres")
	private String descripcion;
	
	
	private MultipartFile archivo;


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public MultipartFile getArchivo() {
		return archivo;
	}
	public void setArchivo(MultipartFile archivo) {
		this.archivo = archivo;
	}

}
