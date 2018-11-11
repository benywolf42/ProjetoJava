package com.biblioteca.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.primefaces.context.RequestContext;

import com.biblioteca.dao.FuncionarioDAO;
import com.biblioteca.model.Funcionario;
import com.biblioteca.model.Perfil;
import com.biblioteca.util.SessionContext;
 
@ManagedBean
public class LoginBean {
    Funcionario funcionario = new Funcionario();
    FuncionarioBean bean = new FuncionarioBean();
    SessionContext session = SessionContext.getInstance();
    FuncionarioDAO dao = new FuncionarioDAO();
    
    public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
   
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        
        
        if(funcionario.getNome() != null && funcionario.getSenha() != null) {
            loggedIn = true;

            try {
            	
            	Perfil perfil = dao.validarLogin(funcionario);
            	if(perfil != null){
            		session.setAttribute("acesso",perfil.getAcesso());
            		session.setAttribute("usuario",funcionario.getNome());
    				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");            		
            	}else {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Erro", "Usuário ou Senha Invalidos");
                    FacesContext.getCurrentInstance().addMessage(null, message);
            	}
            	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
         
       // FacesContext.getCurrentInstance().addMessage(null, message);
       // context.addCallbackParam("loggedIn", loggedIn);

    }
    
    public String getUsuarioLogado(){
    	return (String) session.getAttribute("usuario");
    }
    
    public void logoff(){
    	session.encerrarSessao();
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
}