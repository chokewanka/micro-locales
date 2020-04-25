package pe.com.chokewanka.springboot.micro.locales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.chokewanka.springboot.micro.locales.filter.LocalFilter;
import pe.com.chokewanka.springboot.micro.locales.model.Ambiente;
import pe.com.chokewanka.springboot.micro.locales.model.Local;
import pe.com.chokewanka.springboot.micro.locales.repository.AmbienteRepository;
import pe.com.chokewanka.springboot.micro.locales.repository.LocalRepository;
import pe.com.chokewanka.springboot.micro.locales.service.LocalService;
import pe.com.chokewanka.springboot.micro.locales.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.locales.utils.UtilConstants;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Local> findAll() {
		List<Local> dbLocales = (List<Local>) localRepository.findAll();
		
		List<Local> locales = new ArrayList<Local>();
		for(Local dbLocal : dbLocales) {
			Local local = new Local();
			
			local.setId(dbLocal.getId());
			local.setNombre(dbLocal.getNombre());
			local.setDireccion(dbLocal.getDireccion());
			
			locales.add(local);
		}
		
		return locales;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Local> filter(Map<String, String> filters) {
		LocalFilter localFilter = new LocalFilter();
		
		int totalFilters = 0;
		for(Map.Entry<String, String> filter : filters.entrySet()) {
			String key = filter.getKey();
			String value = filter.getValue();
			
			switch (key) {
			case ModelConstants.LOCAL_NOMBRE:
				if(value != null && !value.trim().equals(UtilConstants.EMPTY_STRING)) {
					localFilter.setNombre(value.trim());
					totalFilters++;
				}
				
				break;
			default:
				return new ArrayList<Local>();
			}
			
		}
		
		if (totalFilters > 0) {
			List<Local> dbLocales = localRepository.findCustom(localFilter);
			
			List<Local> locales = new ArrayList<Local>();
			for(Local dbLocal : dbLocales) {
				Local local = new Local();
				
				local.setId(dbLocal.getId());
				local.setNombre(dbLocal.getNombre());
				local.setDireccion(dbLocal.getDireccion());
				
				locales.add(local);
			}
			
			return locales;
		}
		else {
			return new ArrayList<Local>();
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Local findById(Long id) {
		Optional<Local> optionalLocal = localRepository.findById(id);
		
		if(optionalLocal.isPresent()) {
			Local dbLocal = optionalLocal.get();
			Local local = new Local();
			
			local.setId(dbLocal.getId());
			local.setNombre(dbLocal.getNombre());
			local.setDireccion(dbLocal.getDireccion());
			
			for(Ambiente dbAmbiente : dbLocal.getAmbientes()) {
				Ambiente ambiente = new Ambiente();
				ambiente.setId(dbAmbiente.getId());
				ambiente.setNombre(dbAmbiente.getNombre());
				
				local.getAmbientes().add(ambiente);
			}
			
			return local;
		}
		else {
			return new Local();
		}
	}

	@Override
	@Transactional
	public Long create(Local local) {
		if(local.getId() == null || local.getId().equals(UtilConstants.EMPTY_ID)) {
			Local newLocal = new Local();
			
			newLocal.setNombre(local.getNombre());
			newLocal.setDireccion(local.getDireccion());
			newLocal.setIsDeleted(UtilConstants.IS_NOT_DELETED);
			
			List<Ambiente> arrAmbiente = new ArrayList<Ambiente>();
			for(Ambiente ambiente : local.getAmbientes()) {
				Optional<Ambiente> optAmbiente = ambienteRepository.findById(ambiente.getId());
				
				if(optAmbiente.isPresent()) {
					Ambiente curAmbiente = optAmbiente.get();
					
					curAmbiente.getLocales().add(newLocal);
					
					arrAmbiente.add(curAmbiente);
				}
			}
			newLocal.setAmbientes(arrAmbiente);
			
			newLocal = localRepository.save(newLocal);
			return newLocal.getId();
		}
		else {
			return 0L;
		}
	}
	
	@Override
	@Transactional
	public void edit(Long id, Local local) {
		if(id != null && !id.equals(UtilConstants.EMPTY_ID)) {
			Optional<Local> optLocal = localRepository.findById(id);
			
			if(optLocal.isPresent()) {
				Local curLocal = optLocal.get();
				
				curLocal.setNombre(local.getNombre());
				curLocal.setDireccion(local.getDireccion());

				List<Ambiente> arrAmbiente = new ArrayList<Ambiente>();
				for(Ambiente ambiente : local.getAmbientes()) {
					Optional<Ambiente> optAmbiente = ambienteRepository.findById(ambiente.getId());
					
					if(optAmbiente.isPresent()) {
						Ambiente curAmbiente = optAmbiente.get();
						
						int i = curAmbiente.getLocales().indexOf(curLocal);
						if(i >= 0) {
							curAmbiente.getLocales().set(i, curLocal);
						}
						else {
							curAmbiente.getLocales().add(curLocal);
						}
						
						arrAmbiente.add(curAmbiente);
					}
				}
				curLocal.setAmbientes(arrAmbiente);
				
				localRepository.save(curLocal);
			}
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Optional<Local> optionalLocal = localRepository.findById(id);
		
		if(optionalLocal.isPresent()) {
			Local local = optionalLocal.get();
			
			local.setIsDeleted(UtilConstants.IS_DELETED);
			
			localRepository.save(local);
		}
	}

}
