package com.sogou.adcore.bootconfig.jpa;

import java.io.Serializable;

/**
 * @author by zy.
 */
public interface BaseRepository<T,ID extends Serializable>{
    T valueOf(ID id);
}