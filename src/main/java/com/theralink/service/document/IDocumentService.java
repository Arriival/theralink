package com.theralink.service.document;

import com.theralink.service.IGenericService;
import com.theralink.domain.Document;

import java.util.List;

public interface IDocumentService extends IGenericService<Document, String> {
	List<Document> list(String className, String objectId);
}
