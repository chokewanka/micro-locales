package pe.com.chokewanka.springboot.micro.locales.service;

import java.util.List;

import pe.com.chokewanka.springboot.micro.locales.model.Ambiente;

public interface AmbienteService {

	public List<Ambiente> findAll();
	
	public Ambiente findById(Long id);
	
}
