/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Funcionario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class FuncionarioDAOImp extends BaseDAOImp<Funcionario, Long>
        implements FuncionarioDAO {

    @Override
    public Funcionario pesquisaPorId(Long id) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Funcionario cidade = (Funcionario) session.get(Funcionario.class, id);
        session.close();
        return cidade;
    }

    @Override
    public List<Funcionario> getTodos() {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Funcionario");
        List<Funcionario> cidades = query.list();
        session.close();
        return cidades;
    }

    @Override
    public List<Funcionario> pesquisaLikeNome(String nome) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Funcionario f WHERE f.nome like :valor");
        query.setString("valor", "%" + nome + "%");
        List<Funcionario> funcionarios = query.list();
        session.close();
        return funcionarios;    }
}
