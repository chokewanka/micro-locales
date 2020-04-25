package pe.com.chokewanka.springboot.micro.locales.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pe.com.chokewanka.springboot.micro.locales.model.Local;
import pe.com.chokewanka.springboot.micro.locales.service.LocalService;

@RestController
@RequestMapping("/local")
public class LocalController {

	@Autowired
	private LocalService localService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Local> listar(){
		return localService.findAll();
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public List<Local> filtrar( @RequestParam Map<String,String> filters ){
		return localService.filter(filters);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Local ver(@PathVariable Long id){
		return localService.findById(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Long crear(@RequestBody Local local) {
		return localService.create(local);
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void editar(@PathVariable Long id, @RequestBody Local local) {
		localService.edit(id, local);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		localService.delete(id);
	}
	
}
