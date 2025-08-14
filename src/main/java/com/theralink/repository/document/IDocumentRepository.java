package com.theralink.repository.document;

import com.core.framework.repository.IGenericRepository;
import com.theralink.domain.Document;
import com.theralink.domain.InsuranceTariff;

import java.util.List;

public interface IDocumentRepository extends IGenericRepository<Document, String> {
	List<Document> getAllByClassNameAndObjectId(String className, String objectId);
}
