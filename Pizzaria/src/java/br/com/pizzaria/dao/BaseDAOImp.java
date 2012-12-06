/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.util.FabricaSessao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Aluno
 */
public class BaseDAOImp<T, ID> implements BaseDAO<T, ID> {

   protected Session session;
    protected Transaction transaction;

    @Override
    public T salva(T entidade) {
        abreSessao();
        session.save(entidade);
        fechaSessao();
        return entidade;
    }

    @Override
    public void altera(T entidade) {
        abreSessao();
        session.update(entidade);
        fechaSessao();
    }

    @Override
    public void remove(T entidade) {
        abreSessao();
        session.delete(entidade);
        fechaSessao();
    }

    protected void abreSessao() {
        SessionFactory sf = FabricaSessao.abreSessao();
        session = sf.openSession();
        transaction = session.beginTransaction();
    }

    protected void fechaSessao() {
        transaction.commit();
        session.close();
    }

    @Override
    public T pesquisaPorId(ID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<T> getTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

