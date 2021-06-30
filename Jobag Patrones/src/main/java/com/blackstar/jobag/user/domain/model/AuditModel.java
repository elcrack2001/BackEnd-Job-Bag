package com.blackstar.jobag.user.domain.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class AuditModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",nullable = false,updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updateAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public AuditModel setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public AuditModel setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}
