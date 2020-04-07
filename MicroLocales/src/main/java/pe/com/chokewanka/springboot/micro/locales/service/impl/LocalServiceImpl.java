package pe.com.chokewanka.springboot.micro.locales.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.chokewanka.springboot.micro.locales.filter.LocalFilter;
import pe.com.chokewanka.springboot.micro.locales.model.Local;
import pe.com.chokewanka.springboot.micro.locales.repository.LocalRepository;
import pe.com.chokewanka.springboot.micro.locales.service.LocalService;
import pe.com.chokewanka.springboot.micro.locales.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.locales.utils.UtilConstants;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	private LocalRepository localRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Local> findAll() {
		return (List<Local>) localRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Local> filter(Map<String, String> filters) {
		
		LocalFilter localFilter = new LocalFilter();
		
		String key;
		String value;
		int totalFilters = 0;
		
		for(Map.Entry<String, String> filter : filters.entrySet()) {
			
			key = filter.getKey();
			value = filter.getValue();
			
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
			return (List<Local>) localRepository.findCustom(localFilter);
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
			return optionalLocal.get();
		}
		else {
			return new Local();
		}
		
	}

	@Override
	@Transactional
	public Local save(Local local) {
		
		Long id = local.getId();
		if(id == null || id.equals(UtilConstants.EMPTY_ID)) {
			local.setIsDeleted(UtilConstants.IS_NOT_DELETED);
		}
		
		Optional<Local> optionalLocal = localRepository.findById(id);
		
		if(optionalLocal.isPresent()) {
			local.setIsDeleted(UtilConstants.IS_DELETED);
			return localRepository.save(local);
		}
		else {
			return new Local();
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
