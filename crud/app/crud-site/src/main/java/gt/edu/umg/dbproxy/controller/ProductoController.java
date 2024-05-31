package gt.edu.umg.dbproxy.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import gt.edu.umg.dbproxy.model.Producto;
import gt.edu.umg.dbproxy.model.ProductoDto;
import gt.edu.umg.dbproxy.service.ProductoRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin/productos")
public class ProductoController {

	@Autowired
	private ProductoRepository repo;
	
	@GetMapping({"", "/"})
	public String mostrarListaProductos(Model model) {
		List<Producto> productos = repo.findByActivo(true);
		model.addAttribute("productos", productos);
		
		return "productos/index";
	}
	
	@GetMapping("crear")
	public String mostrarCrearProducto(Model model) {
		ProductoDto productoDto = new ProductoDto();
		model.addAttribute("productoDto", productoDto);
		
		return "productos/crearProducto";
	}
	
	@PostMapping("crear")
	public String crearProducto(@Valid @ModelAttribute ProductoDto productoDto, BindingResult resultado) {
		
		if(productoDto.getArchivo().isEmpty()) {
			resultado.addError(new FieldError("productoDto", "archivo", "La imagen del producto es requerida."));
		}
		
		if(resultado.hasErrors()) {
			return "productos/crearProducto";
		}
		
		MultipartFile imagen = productoDto.getArchivo();
		Date fechaCreacion = new Date();
		String nombreArchivo = fechaCreacion.getTime() + "_" + imagen.getOriginalFilename();
		
		try {
			String directorioCarga = "public/imagenes/";
			Path rutaCarga = Paths.get(directorioCarga);
			
			if(Files.exists(rutaCarga)) {
				Files.createDirectories(rutaCarga);
			}
			
			try(InputStream inputStream = imagen.getInputStream()) {
				Files.copy(inputStream, Paths.get(directorioCarga + nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		Producto producto = new Producto();
		producto.setNombre(productoDto.getNombre());
		producto.setMarca(productoDto.getMarca());
		producto.setCategoria(productoDto.getCategoria());
		producto.setPrecio(productoDto.getPrecio());
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setFechaCreacion(fechaCreacion);
		producto.setNombreArchivo(nombreArchivo);
		producto.setActivo(true);
		
		repo.save(producto);
		
		return "redirect:/admin/productos";
	}
	
	@GetMapping("editar")
	public String mostrarEditarProducto(Model model, @RequestParam int id) {
		
		try {
			Producto producto = repo.findById(id).get();
			model.addAttribute("producto", producto);
			
			ProductoDto productoDto = new ProductoDto();
			productoDto.setNombre(producto.getNombre());
			productoDto.setMarca(producto.getMarca());
			productoDto.setCategoria(producto.getCategoria());
			productoDto.setPrecio(producto.getPrecio());
			productoDto.setDescripcion(producto.getDescripcion());
			
			model.addAttribute("productoDto", productoDto);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return "redirect:/admin/productos";
		}
		
		return "productos/editarProducto";
	}
	
	@PostMapping("editar")
	public String editarProducto(
			Model model, 
			@RequestParam int id,
			@Valid @ModelAttribute ProductoDto productoDto, 
			BindingResult resultado) {
		
		try {
			Producto producto = repo.findById(id).get();
			model.addAttribute("producto", producto);
			
			if(resultado.hasErrors()) {
				return "productos/editarProducto";
			}
			
			Date fechaCreacion = new Date();
			if(!productoDto.getArchivo().isEmpty()) {
				
				String directorioCarga = "public/imagenes/";
				Path rutaAntiguoArchivo = Paths.get(directorioCarga + producto.getNombreArchivo());
				
				try {
					Files.delete(rutaAntiguoArchivo);
				} catch (Exception e) {
					System.out.println("Fallo eliminando archivo antiguo: " + e.getMessage());
				}
				
				MultipartFile imagen = productoDto.getArchivo();
				String nombreArchivo = fechaCreacion.getTime() + "_" + imagen.getOriginalFilename();
				
				try(InputStream inputStream = imagen.getInputStream()) {
					Files.copy(inputStream, Paths.get(directorioCarga + nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
				}
				
				producto.setNombreArchivo(nombreArchivo);
			}
			
			producto.setNombre(productoDto.getNombre());
			producto.setMarca(productoDto.getMarca());
			producto.setCategoria(productoDto.getCategoria());
			producto.setPrecio(productoDto.getPrecio());
			producto.setDescripcion(productoDto.getDescripcion());
			producto.setFechaActualizacion(fechaCreacion);
			
			repo.save(producto);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return "redirect:/admin/productos";
	}
	
	@GetMapping("eliminar")
	public String eliminarProducto(Model model, @RequestParam int id) {
		
		try {
			Producto producto = repo.findById(id).get();
			producto.setActivo(false);
			
			repo.save(producto);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return "redirect:/admin/productos";
	}
}
