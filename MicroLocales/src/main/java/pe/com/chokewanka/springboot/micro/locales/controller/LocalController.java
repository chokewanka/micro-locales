package pe.com.chokewanka.springboot.micro.locales.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Local ver(@PathVariable Long id) throws Exception {
		return localService.findById(id);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Local guardar(@RequestBody Local local) {
		return localService.save(local);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam Long id) {
		localService.delete(id);
	}
	
}
