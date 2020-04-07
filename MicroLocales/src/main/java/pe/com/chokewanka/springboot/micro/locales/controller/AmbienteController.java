package pe.com.chokewanka.springboot.micro.locales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.chokewanka.springboot.micro.locales.model.Ambiente;
import pe.com.chokewanka.springboot.micro.locales.service.AmbienteService;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {

	@Autowired
	private AmbienteService ambienteService;
	
	@GetMapping("/")
	public List<Ambiente> listar(){
		return ambienteService.findAll();
	}
	
}
