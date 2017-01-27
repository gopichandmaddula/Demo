package imcs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="AUTHORITY")
public class Authority {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long authority_id;
	
	private String authority;
	
	@ManyToOne
	@JoinColumn(name="credentials_id")
	private Credentials credential;
	
	public Authority() {}
	
	public Authority(Long id, String authority) {
		super();
		this.authority_id = id;
		this.authority = authority;
	}
	
	public Authority(Long id, String authority, Credentials credential) {
		super();
		this.authority_id = id;
		this.authority = authority;
		this.credential = credential;
	}

	public Long getId() {
		return authority_id;
	}
	public void setId(Long id) {
		this.authority_id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Credentials getCredential() {
		return credential;
	}

	public void setCredential(Credentials credential) {
		this.credential = credential;
	}

	@Override
	public String toString() {
		return "Authority [id=" + authority_id + ", authority=" + authority  + "]";
	}	
}
