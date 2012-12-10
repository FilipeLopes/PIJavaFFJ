/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Sabor;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface SaborDAO extends BaseDAO<Sabor, Long>{
    
    List<Sabor> pesquisaLikeSabor(String sabor);
    
}
