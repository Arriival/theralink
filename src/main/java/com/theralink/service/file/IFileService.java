package com.theralink.service.file;

import com.theralink.domain.File;
import com.theralink.service.IGenericService;

import java.util.List;

public interface IFileService extends IGenericService<File, String> {
	List<File> list(String className, String objectId);
}
