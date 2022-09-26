package domain;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

	public Admin(String name, String surname, String username, String password, String email) {
		super(name, surname, username, password, email);
	}

	@Override
	public boolean isAdmin() {
		return true;
	}
	
}
