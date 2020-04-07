package pe.com.chokewanka.springboot.micro.locales.service;

import java.util.List;
import java.util.Map;

import pe.com.chokewanka.springboot.micro.locales.model.Local;

public interface LocalService {

	public List<Local> findAll();
	
	public List<Local> filter(Map<String,String> filters);
	
	public Local findById(Long id);
	
	public Local save(Local local);
	
	public void delete(Long id);
	
}
