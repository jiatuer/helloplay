package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	public String email;
	public String password;
	public String fullname;
	public String isAdmin;

	public User(String email, String password, String fullname) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}

	public static User connect(String email, String password) {
		return User.find("byEmailLikeAndPassword", "%" + email + "%", password)
				.first();
	}

}
