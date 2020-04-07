package pe.com.chokewanka.springboot.micro.locales.repository;

import org.springframework.data.repository.CrudRepository;

import pe.com.chokewanka.springboot.micro.locales.model.Local;
import pe.com.chokewanka.springboot.micro.locales.repository.custom.LocalRepositoryCustom;

public interface LocalRepository extends CrudRepository<Local, Long>, LocalRepositoryCustom {

}
