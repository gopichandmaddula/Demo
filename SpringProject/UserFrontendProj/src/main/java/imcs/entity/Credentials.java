package imcs.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CREDENTIALS")
public class Credentials {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long credentials_id;

	private String username;
	private String password;

	//@Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(mappedBy = "credential", cascade = CascadeType.ALL, orphanRemoval=true, targetEntity=Authority.class,
				fetch = FetchType.EAGER)
	private List<Authority> authorities;

	@OneToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;

	public Credentials() {
	}

	public Credentials(String username, String password, List<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	

	public Long getCredentials_id() {
		return credentials_id;
	}

	public void setCredentials_id(Long credentials_id) {
		this.credentials_id = credentials_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Credentials [username=" + username + ", password=" + password + ", authorities=" + authorities + "]";
	}
}
