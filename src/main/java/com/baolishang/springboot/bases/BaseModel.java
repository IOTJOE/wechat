package com.baolishang.springboot.bases;

import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
* Created by will on 16/9/12.
*/

@NoRepositoryBean
public abstract class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private transient Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseModel() {
    }
}
