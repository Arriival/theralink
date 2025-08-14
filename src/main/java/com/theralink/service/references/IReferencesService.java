package com.theralink.service.references;

import com.core.framework.service.IGenericService;
import com.theralink.domain.References;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReferencesService extends IGenericService<References, String> {
	Page<References> referredToGrid(Pageable pageable);

	Page<References> referredFromGrid(String customerId, Pageable pageable);

	String visit(String id);
}
