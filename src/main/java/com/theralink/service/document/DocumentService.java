package com.theralink.service.document;

import com.core.framework.repository.IGenericRepository;
import com.core.framework.service.GenericService;
import com.theralink.domain.Document;
import com.theralink.repository.document.IDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService extends GenericService<Document, String> implements IDocumentService {

	@Autowired
	private IDocumentRepository iDocumentRepository;
	@Override
	protected IGenericRepository<Document, String> getGenericRepo() {
		return iDocumentRepository;
	}

	@Override
	public List<Document> list(String className, String objectId) {
		return iDocumentRepository.getAllByClassNameAndObjectId(className, objectId);
	}
}
