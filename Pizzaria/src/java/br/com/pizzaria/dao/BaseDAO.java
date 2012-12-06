/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import java.util.List;

/**
 *
 * @author Aluno
 */
public interface BaseDAO <T, ID> {
    
    T salva(T entidade);
    
    T pesquisaPorId(ID id);
    
    void remove(T entidade);
    
    void altera(T entidade);
    
    List<T> getTodos();
    
}
