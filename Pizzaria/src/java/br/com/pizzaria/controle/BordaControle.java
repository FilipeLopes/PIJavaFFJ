/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.BordaDAO;
import br.com.pizzaria.dao.BordaDAOImp;
import br.com.pizzaria.entidade.Borda;
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
public class BordaControle {  
    
    private Borda borda;
    private BordaDAO bordaDAO;
    private DataModel model;
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Borda getCidade() {
     if(borda == null){
            borda = new Borda();
        }
        return borda;
    }

    public void setCidade(Borda borda) {
        this.borda = borda;
    }

    public BordaDAO getbordaDAO() {
        return bordaDAO;
    }

    public void setbordaDAO(BordaDAO bordaDAO) {
        this.bordaDAO = bordaDAO;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        bordaDAO = new BordaDAOImp();
        if (borda.getId() == null) {
            bordaDAO.salva(borda);            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Borda salva com sucesso!", ""));
        } else {
            bordaDAO.altera(borda);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Borda alterada com sucesso!", ""));
        }
        limpar();
        return "pesqBorda";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            bordaDAO = new BordaDAOImp();
            borda = (Borda) model.getRowData();            
            bordaDAO.remove(borda);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Borda excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        borda = (Borda) model.getRowData();
        setCidade(borda);
        return "cadBorda";
    }
    
    public String novoCidade() {        
        borda = new Borda();
        return "cadBorda";
    }

    private void limpar() {
        borda = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqBorda";
    }
}
