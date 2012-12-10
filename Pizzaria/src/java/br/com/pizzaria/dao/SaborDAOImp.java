/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Sabor;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class SaborDAOImp extends BaseDAOImp<Sabor, Long> implements SaborDAO{

    @Override
    public List<Sabor> pesquisaLikeSabor(String sabor) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Sabor s WHERE s.sabor like :valor");
        query.setString("valor", "%" + sabor + "%");
        List<Sabor> sabores = query.list();
        session.close();
        return sabores;
    }
    
}
