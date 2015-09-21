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

/**
 * Classe Usuário - Classe de uso interno que possibilita o sistema de registro e log-in
 * Cada usuário possui um log-in, uma senha, um role(ADMIN,PROFESSOR,JOGADOR,..), e a instituição ou participante que representa
 * Classe altamente vinculada ao Spring Security - leitura da documentação é recomendado.
 * @author JOGAI
 *
 */
//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue
	Long id;

	//@OneToOne, @ManyToMany, @ManyToOne e @OneToMany são algumas das anotações que designam os tipos de relações
	//que existem entre as classes
	//Existe um pdf no Dropbox que explica melhor cada anotação
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	private Usuario() {
	}
	
	//Construtores com validadores (que checam o banco de dados)
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

	//Getters e setters
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	//Validadores
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

	public InstituicaoEnsino getInstituicaoEnsino() {
		return this.instituicaoEnsino;
	}
	
	public Participante getParticipante() {
		return participante;
	}
	
	@Override
	public String toString() {
		return this.getSenha() + "\t" + this.getNomeUsuario();
	}
}
