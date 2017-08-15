package com.sogou.adcore.bootconfig.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author by zy.
 */
@NoRepositoryBean
public interface JpaBaseRepository<T,ID extends Serializable>
        extends JpaRepository<T, ID>,
                JpaSpecificationExecutor<T>,
                BaseRepository<T, ID>{
}