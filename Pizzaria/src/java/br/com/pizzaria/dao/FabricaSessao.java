/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author tecnicom
 */
public class FabricaSessao {
    private static SessionFactory sessionFactory;

    public static SessionFactory abreConexao() {
        if (sessionFactory == null) {
            Configuration cfg = new AnnotationConfiguration();
            cfg.configure("/br/com/pizzaria/dao/hibernate.cfg.xml");
            sessionFactory = cfg.buildSessionFactory();
        }
        return sessionFactory;
    }
}
