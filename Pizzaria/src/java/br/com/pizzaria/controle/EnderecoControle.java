/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.EnderecoDAO;
import br.com.pizzaria.dao.EnderecoDAOImp;
import br.com.pizzaria.entidade.Endereco;
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
public class EnderecoControle {
   private Endereco endereco;
    private EnderecoDAO enderecoDAO;
    private DataModel model;
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Endereco getCidade() {
     if(endereco == null){
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setCidade(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoDAO getenderecoDAO() {
        return enderecoDAO;
    }

    public void setenderecoDAO(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        enderecoDAO = new EnderecoDAOImp();
        if (endereco.getId() == null) {
            enderecoDAO.salva(endereco);            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Endereco salvo com sucesso!", ""));
        } else {
            enderecoDAO.altera(endereco);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Endereco alterado com sucesso!", ""));
        }
        limpar();
        return "pesqEndereco";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            enderecoDAO = new EnderecoDAOImp();
            endereco = (Endereco) model.getRowData();            
            enderecoDAO.remove(endereco);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Endereco excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel a exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        endereco = (Endereco) model.getRowData();
        setCidade(endereco);
        return "cadEndereco";
    }
    
    public String novoCidade() {        
        endereco = new Endereco();
        return "cadEndereco";
    }

    private void limpar() {
        endereco = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqEndereco";
    }
}
