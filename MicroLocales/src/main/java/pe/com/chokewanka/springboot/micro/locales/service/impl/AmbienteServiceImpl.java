package pe.com.chokewanka.springboot.micro.locales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.chokewanka.springboot.micro.locales.model.Ambiente;
import pe.com.chokewanka.springboot.micro.locales.repository.AmbienteRepository;
import pe.com.chokewanka.springboot.micro.locales.service.AmbienteService;

@Service
public class AmbienteServiceImpl implements AmbienteService {

	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@Override
	public List<Ambiente> findAll() {
		List<Ambiente> dbAmbientes = (List<Ambiente>) ambienteRepository.findAll();
		
		List<Ambiente> ambientes = new ArrayList<Ambiente>();
		for(Ambiente dbAmbiente : dbAmbientes) {
			Ambiente ambiente = new Ambiente();
			
			ambiente.setId(dbAmbiente.getId());
			ambiente.setNombre(dbAmbiente.getNombre());
			
			ambientes.add(ambiente);
		}
		
		return ambientes;
	}

	@Override
	public Ambiente findById(Long id) {
		Optional<Ambiente> optionalAmbiente = ambienteRepository.findById(id);
		
		if(optionalAmbiente.isPresent()) {
			Ambiente dbAmbiente = optionalAmbiente.get();
			Ambiente ambiente = new Ambiente();
			
			ambiente.setId(dbAmbiente.getId());
			ambiente.setNombre(dbAmbiente.getNombre());
		
			return ambiente;
		}
		else {
			return new Ambiente();
		}
	}

}
