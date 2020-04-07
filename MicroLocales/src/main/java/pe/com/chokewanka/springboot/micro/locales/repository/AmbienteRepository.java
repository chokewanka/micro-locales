package pe.com.chokewanka.springboot.micro.locales.repository;

import org.springframework.data.repository.CrudRepository;

import pe.com.chokewanka.springboot.micro.locales.model.Ambiente;

public interface AmbienteRepository extends CrudRepository<Ambiente, Long> {
	
}
