package com.gg.examples.HibernateExample.model;

import javax.persistence.*;

/**
 * User: adurmaz
 * Date: 5/26/13
 * Time: 11:41 AM
 */
@Entity(name = "Foo")
@Table(name = "T_FOO")
public class Foo {

    /*
    * Hibernate tin access stratejisi Id annatasyonunun yeri field
    * üzerinde mi yoksa method üzerinde mi ona göre belirliyor.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob // Clob bir alana karşılık gelmesi için
    @Column(name="C_NAME")
    private String name;

    @Embedded
    private Bar bar;

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

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
