package com.theralink.repository.file;

import com.theralink.domain.File;
import com.theralink.repository.IGenericRepository;

import java.util.List;

public interface IFileRepository extends IGenericRepository<File, String> {
	List<File> getAllByClassNameAndObjectId(String className, String objectId);
}
