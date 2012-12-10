/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Borda;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface BordaDAO extends BaseDAO<Borda, Long>{
    
    List<Borda> pesquisaLikeBorda(String borda);
    
}
