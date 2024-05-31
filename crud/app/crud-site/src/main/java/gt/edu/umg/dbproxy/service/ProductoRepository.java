package gt.edu.umg.dbproxy.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.umg.dbproxy.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	public List<Producto> findByActivo(boolean activo);
}
