/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.PessoaDAO;
import br.com.pizzaria.dao.PessoaDAOImp;
import br.com.pizzaria.entidade.Pessoa;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class PessoaControle {
   private Pessoa pessoa;
    private PessoaDAO pessoaDAO;
    private DataModel model;
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Pessoa getCidade() {
     if(pessoa == null){
            pessoa = new Pessoa();
        }
        return pessoa;
    }

    public void setCidade(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaDAO getpessoaDAO() {
        return pessoaDAO;
    }

    public void setpessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        pessoaDAO = new PessoaDAOImp();
        if (pessoa.getId() == null) {
            pessoaDAO.salva(pessoa);            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pessoa salva com sucesso!", ""));
        } else {
            pessoaDAO.altera(pessoa);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pessoa alterada com sucesso!", ""));
        }
        limpar();
        return "pesqPessoa";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            pessoaDAO = new PessoaDAOImp();
            pessoa = (Pessoa) model.getRowData();            
            pessoaDAO.remove(pessoa);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pessoa excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        pessoa = (Pessoa) model.getRowData();
        setCidade(pessoa);
        return "cadPessoa";
    }
    
    public String novoCidade() {        
        pessoa = new Pessoa();
        return "cadPessoa";
    }

    private void limpar() {
        pessoa = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqPessoa";
    }
}
