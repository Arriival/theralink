package com.repository.setting;

import com.core.framework.repository.IGenericRepository;
import com.domain.Setting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISettingRepository extends IGenericRepository<Setting, String> {
    @Query("from Setting e where e.key = :key")
    Setting loadByKey(@Param("key") String key);

	@Query("from Setting e")
	List<Setting> list();
}
