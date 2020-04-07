package pe.com.chokewanka.springboot.micro.locales.repository.custom;

import java.util.List;

import pe.com.chokewanka.springboot.micro.locales.filter.LocalFilter;
import pe.com.chokewanka.springboot.micro.locales.model.Local;

public interface LocalRepositoryCustom {

	public List<Local> findCustom( LocalFilter filter );
	
}
