package example.springmvc.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User and account data.
 * 
 * @author Daniel
 *
 */
@Entity
@Table(name="user_table")
public class User {
	
	public User(){}
	
	@Id
	@Column(name="id")
	private String id = "";

	@Column(name="password")
	private String password = "";

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
