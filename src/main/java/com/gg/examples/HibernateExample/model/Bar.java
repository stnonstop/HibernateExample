package com.gg.examples.HibernateExample.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * User: adurmaz
 * Date: 5/26/13
 * Time: 2:16 PM
 */
//@Embeddable /*Eğer kullanılan yerde @Embedded tanımlarsa burada belirtmeye gerek yok.*/
public class Bar {
    @Column(nullable = true)
    private boolean b;

    private Baz baz;

    @Transient // Bal ı tablo ya oluşturma demek için
    private Bal bal;
}
