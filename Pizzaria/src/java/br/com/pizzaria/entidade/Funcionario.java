/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Aluno
 */
@Entity
public class Funcionario extends Pessoa{
    
    @Column(length=50, nullable=false)
    private String cracha;
    
    @ManyToOne
    @JoinColumn(name="id_funcao")
    private Funcao funcao;

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funca) {
        this.funcao = funca;
    }
    
    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }
    
    
    
    
}
