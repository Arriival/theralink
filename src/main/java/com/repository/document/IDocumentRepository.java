package com.repository.document;

import com.core.framework.repository.IGenericRepository;
import com.domain.Document;
import com.domain.InsuranceTariff;

import java.util.List;

public interface IDocumentRepository extends IGenericRepository<Document, String> {
	List<Document> getAllByClassNameAndObjectId(String className, String objectId);
}
