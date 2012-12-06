/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Funcao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class FuncaoDAOImp extends BaseDAOImp<Funcao, Long> 
       implements FuncaoDAO{

    @Override
    public Funcao pesquisaPorId(Long id) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Funcao Funcao = (Funcao) session.get(Funcao.class, id);
        session.close();
        return Funcao;
    }

    @Override
    public List<Funcao> getTodos() {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Funcao");
        List<Funcao> funcoes = query.list();
        session.close();
        return funcoes;
    }

    @Override
    public List<Funcao> pesquisaLikeNome(String nome) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Funcao f WHERE f.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Funcao> funcoes = query.list();
        session.close();
        return funcoes;
    }

}
