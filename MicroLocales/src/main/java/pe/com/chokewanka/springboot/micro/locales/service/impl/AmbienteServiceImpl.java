package pe.com.chokewanka.springboot.micro.locales.service.impl;

import java.util.List;

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
		return (List<Ambiente>) ambienteRepository.findAll();
	}

	@Override
	public Ambiente findById(Long id) {
		return ambienteRepository.findById(id).orElse(null);
	}

}
