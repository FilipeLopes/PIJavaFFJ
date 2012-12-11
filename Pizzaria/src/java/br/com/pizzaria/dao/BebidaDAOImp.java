/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Bebida;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class BebidaDAOImp extends BaseDAOImp<Bebida, Long> implements BebidaDAO{
    
            @Override
    public Bebida pesquisaPorId(Long id) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Bebida bebida = (Bebida) session.get(Bebida.class, id);
        session.close();
        return bebida;
    }

    @Override
    public List<Bebida> getTodos() {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Bebida");
        List<Bebida> clientes = query.list();
        session.close();
        return clientes;
    }
    
        @Override
        public List<Bebida> pesquisaLikeNome(String nome) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Bebida b WHERE b.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Bebida> bebidas = query.list();
        session.close();
        return bebidas;
    }
}
