package br.cefetrj.jogai.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

//import br.uerj.dinfo.acw.dominio.membros.MembroComunidade;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue
	Long id;
	
	@OneToOne(cascade={CascadeType.ALL})
	InstituicaoEnsino instituicaoEnsino;
	
	@OneToOne(cascade={CascadeType.ALL})
	Participante participante;

	@Column(name = "str_login")
	String nomeUsuario;

	@Column(name = "str_senha")
	String senha;

	@Column(name = "str_role")
	String role;

	//@OneToOne
	//MembroComunidade membro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Usuario() {
	}

	public Usuario(String nomeUsuario, String senha, String role/*,
			MembroComunidade membro*/) {
		Validate.notBlank(nomeUsuario, "Nome do usuário deve ser fornecido.");
		Validate.notBlank(senha, "Senha deve ser fornecida.");
		Validate.notBlank(role, "Papel do usuário deve ser fornecido.");
		this.nomeUsuario = nomeUsuario;

		this.senha = criptografar(senha);

		this.role = role;

		this.instituicaoEnsino = null;
		
		this.participante = null;
		
	}
	
	public Usuario(String nomeUsuario, String senha, String role,
			InstituicaoEnsino instituicaoEnsino) {
		Validate.notBlank(nomeUsuario, "Nome do usuário deve ser fornecido.");
		Validate.notBlank(senha, "Senha deve ser fornecida.");
		Validate.notBlank(role, "Papel do usuário deve ser fornecido.");

		this.nomeUsuario = nomeUsuario;

		this.senha = criptografar(senha);

		this.role = role;

		this.instituicaoEnsino = instituicaoEnsino;
		
		this.participante = null;
	}
	
	public Usuario(String nomeUsuario, String senha, String role,
			Participante participante) {
		Validate.notBlank(nomeUsuario, "Nome do usuário deve ser fornecido.");
		Validate.notBlank(senha, "Senha deve ser fornecida.");
		Validate.notBlank(role, "Papel do usuário deve ser fornecido.");

		this.nomeUsuario = nomeUsuario;

		this.senha = criptografar(senha);

		this.role = role;

		this.instituicaoEnsino = null;
		
		this.participante = participante;
	}

	public String getSenha() {
		return senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/*public MembroComunidade getMembro() {
		return membro;
	}*/

	/*public void setMembro(MembroComunidade membro) {
		Validate.notNull(membro, "Membro da comunidade é obrigatório.");
		this.membro = membro;
	}*/

	public String getRole() {
		return role;
	}

	public boolean ehSenhaCorreta(String password) {
		return BCrypt.checkpw(password, this.getSenha());
	}

	public static String criptografar(String valor) {
		return BCrypt.hashpw(valor, BCrypt.gensalt());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> lista = new ArrayList<GrantedAuthority>();
		lista.add(new GrantedAuthorityImpl(role));
		return lista;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.nomeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
