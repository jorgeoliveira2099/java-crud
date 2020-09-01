package br.com.localhost.bean;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.localhost.controller.TelefoneControle;
import br.com.localhost.controller.UsuarioControle;
import br.com.localhost.model.Telefone;
import br.com.localhost.model.Usuario;
import br.com.localhost.util.Constantes;
import br.com.localhost.util.Util;

@ManagedBean
@ViewScoped
public class ManterUsuarioMbean {
	private List<Usuario> colecaoUsuario;
	
	private Usuario usuarioNovo;
	private Usuario usuarioSelecionado;
	
	private Telefone telefoneNovo;
	private Telefone telefoneSelecionado;
	
	private UsuarioControle usuarioControle = UsuarioControle.getInstance();
	private TelefoneControle telefoneControle = TelefoneControle.getInstance();
	
	private String mensagemUsuarioComTelefones = Constantes.MENSAGEM_USUARIO_COM_TELEFONES;
	private Boolean mostrarMensagemExclusao = false;
	private Util util = new Util();

	@PostConstruct
	public void inicializar() {
		limparCampos();
		try {
			colecaoUsuario = usuarioControle.listarUsuarios();
			for (Usuario usuario : colecaoUsuario) {
				usuario.setTelefones(telefoneControle.listarTelefonesPorUsuario(usuario));
			}
		} catch (RuntimeException erro) {
			util.getMenssagemErro();
			erro.printStackTrace();
		}

	}

	public void salvarUsuario() {
		try { 
			if (usuarioControle.verificaSeExisteEmail(usuarioNovo.getEmail())) {
				util.getMenssagemAlerta("Esse email já está vinculado a outro usuário!");
			} else {
			usuarioControle.salvar(usuarioNovo);
			colecaoUsuario.add(usuarioNovo);
			util.fecharDialogPF("dialogCadastrarUsuario");
			util.getMenssagemInfor("Usuário adicionado com sucesso!");
			}
		} catch (RuntimeException erro) {
			util.getMenssagemErro();
			erro.printStackTrace();
		}
		
	}
	
	public void alterarUsuario() {
		try {
			usuarioControle.alterarUsuario(usuarioSelecionado);
			util.fecharDialogPF("dialogAlterarUsuario");
			util.getMenssagemInfor("Usuário alterado com sucesso!");
		} catch (RuntimeException erro) {
			util.getMenssagemErro();
			erro.printStackTrace();
		}
	}
	
	public void excluirUsuario () {
		try {
			usuarioControle.excluirUsuario(usuarioSelecionado);
			colecaoUsuario.remove(usuarioSelecionado);
			util.getMenssagemInfor("Usuário excluído com sucesso!");
		} catch (RuntimeException erro) {
			util.getMenssagemErro();
			erro.printStackTrace();
		}
	}
	
	public void salvarTelefone() {
		try {
			telefoneNovo.setUsuario(usuarioSelecionado);
			telefoneControle.salvarTelefone(telefoneNovo);
			if (telefoneNovo.getUsuario().getTelefones() == null) {
				telefoneNovo.getUsuario().setTelefones(new ArrayList<Telefone>());
			}
			telefoneNovo.getUsuario().getTelefones().add(telefoneNovo);
			util.getMenssagemInfor("Telefone adicionado com sucesso!");
			util.fecharDialogPF("dialogCadastrarTelefone");
		} catch (RuntimeException erro) {
			util.getMenssagemErro();
			erro.printStackTrace();
		}
	}
	
	public void selecionarUsuario (Usuario usuario) {
		telefoneNovo = new Telefone();
		usuarioSelecionado = usuario;
		telefoneNovo.setTipo("Celular");
	}
	
	public void selecionarUsuarioExclusao (Usuario usuario) {
		usuarioSelecionado = usuario;
		if (!usuarioSelecionado.getTelefones().isEmpty() || usuarioSelecionado.getTelefones() == null)
			mostrarMensagemExclusao = true;
		else
			mostrarMensagemExclusao = false;
	}
	
	public void selecionarTelefone (Telefone telefone) {
		telefoneSelecionado = telefone;
	}
	
	public void alterarTelefone() {
		try {
			telefoneControle.alterarTelefone(telefoneSelecionado);
			util.fecharDialogPF("dialogAlterarTelefone");
			util.getMenssagemInfor("Telefone alterado com sucesso!");
		} catch (RuntimeException erro) {
			util.getMenssagemErro();
			erro.printStackTrace();
		}
	}
	
	public void excluirTelefone() {
		try {
			telefoneControle.excluirTelefone(telefoneSelecionado);
			inicializar();
			util.getMenssagemInfor("Telefone excluído com sucesso!");
		} catch (RuntimeException erro) {
			util.getMenssagemErro();
			erro.printStackTrace();
		}
	}
	
	public void limparCampos() {
		usuarioNovo = new Usuario();
		usuarioSelecionado = new Usuario();
		telefoneNovo = new Telefone();
	}

	public List<Usuario> getColecaoUsuario() {
		return colecaoUsuario;
	}

	public void setColecaoUsuario(List<Usuario> colecaoUsuario) {
		this.colecaoUsuario = colecaoUsuario;
	}

	public Usuario getUsuarioNovo() {
		return usuarioNovo;
	}

	public void setUsuarioNovo(Usuario usuarioNovo) {
		this.usuarioNovo = usuarioNovo;
	}

	public Telefone getTelefoneNovo() {
		return telefoneNovo;
	}

	public void setTelefoneNovo(Telefone telefoneNovo) {
		this.telefoneNovo = telefoneNovo;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public String getMensagemUsuarioComTelefones() {
		return mensagemUsuarioComTelefones;
	}

	public void setMensagemUsuarioComTelefones(String mensagemUsuarioComTelefones) {
		this.mensagemUsuarioComTelefones = mensagemUsuarioComTelefones;
	}

	public Boolean getMostrarMensagemExclusao() {
		return mostrarMensagemExclusao;
	}

	public void setMostrarMensagemExclusao(Boolean mostrarMensagemExclusao) {
		this.mostrarMensagemExclusao = mostrarMensagemExclusao;
	}

	public Telefone getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(Telefone telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}
	
}