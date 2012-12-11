/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class ClienteDAOImp extends BaseDAOImp<Cliente, Long> implements ClienteDAO{
    
    @Override
    public Cliente pesquisaPorId(Long id) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Cliente cliente = (Cliente) session.get(Cliente.class, id);
        session.close();
        return cliente;
    }

    @Override
    public List<Cliente> getTodos() {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Cliente");
        List<Cliente> clientes = query.list();
        session.close();
        return clientes;
    }
        @Override
    public List<Cliente> pesquisaLikeNome(String nome) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Cliente c WHERE c.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Cliente> clientes = query.list();
        session.close();
        return clientes;
    }
}
