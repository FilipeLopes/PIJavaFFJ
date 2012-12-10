/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Borda;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class BordaDAOImp extends BaseDAOImp<Borda, Long> implements BordaDAO{

    @Override
    public List<Borda> pesquisaLikeBorda(String borda) {
        session = (Session) FabricaSessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Borda b WHERE b.nome like :valor");
        query.setString("valor", "%" + borda + "%");
        List<Borda> bordas = query.list();
        session.close();
        return bordas;
    }
    
}
