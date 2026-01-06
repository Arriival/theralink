package com.theralink.service.file;

import com.theralink.domain.File;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.file.IFileRepository;
import com.theralink.service.GenericService;

import com.theralink.service.file.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService extends GenericService<File, String> implements IFileService {

	@Autowired
	private IFileRepository iFileRepository;

	@Override
	protected IGenericRepository<File, String> getGenericRepo() {
		return iFileRepository;
	}

	@Override
	public List<File> list(String className, String objectId) {
		return iFileRepository.getAllByClassNameAndObjectId(className, objectId);
	}
}
