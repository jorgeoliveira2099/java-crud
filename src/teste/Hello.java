package teste;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class Hello {

	@PostConstruct
	public void init(){
		System.out.println(" Bean executado! ");
	}

	public String getMessage(){
		return "Hello World JSF!";
	}
	
	public String cadastro(){
		return "cadastro";
	}
	public String voltar(){
		return "index";
	}
	public String login(){
		return "index";
	}


}