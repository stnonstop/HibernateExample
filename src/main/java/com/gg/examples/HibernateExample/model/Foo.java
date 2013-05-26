package com.gg.examples.HibernateExample.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: adurmaz
 * Date: 5/26/13
 * Time: 11:41 AM
 */
@Entity
public class Foo {

    private String name;
    @Id
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
