/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.PizzaDAO;
import br.com.pizzaria.dao.PizzaDAOImp;
import br.com.pizzaria.entidade.Pizza;
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
public class PizzaControle {
    
    private Pizza pizza;
    private PizzaDAO pizzaDAO;
    private DataModel model;
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Pizza getCidade() {
     if(pizza == null){
            pizza = new Pizza();
        }
        return pizza;
    }

    public void setCidade(Pizza pizza) {
        this.pizza = pizza;
    }

    public PizzaDAO getpizzaDAO() {
        return pizzaDAO;
    }

    public void setpizzaDAO(PizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        pizzaDAO = new PizzaDAOImp();
        if (pizza.getId() == null) {
            pizzaDAO.salva(pizza);            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pizza salva com sucesso!", ""));
        } else {
            pizzaDAO.altera(pizza);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pizza alterada com sucesso!", ""));
        }
        limpar();
        return "pesqPizza";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            pizzaDAO = new PizzaDAOImp();
            pizza = (Pizza) model.getRowData();            
            pizzaDAO.remove(pizza);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Pizza excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        pizza = (Pizza) model.getRowData();
        setCidade(pizza);
        return "cadPizza";
    }
    
    public String novoCidade() {        
        pizza = new Pizza();
        return "cadPizza";
    }

    private void limpar() {
        pizza = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqPizza";
    }
}
