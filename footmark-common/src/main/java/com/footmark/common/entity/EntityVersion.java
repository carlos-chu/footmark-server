package com.footmark.common.entity;

/**
 * Used for optimistic lock.
 *
 * @author carlos.chu
 * @version $ v1.0 Jun 24, 2015 $
 */
public interface EntityVersion<IDClass extends java.io.Serializable> {

    /**
     * Speicify the version of the record.
     * 
     * @param version
     */
    public void setVersion(Long version);

    /**
     * Get current version of the record.
     * 
     * @return version
     */
    public Long getVersion();
}
