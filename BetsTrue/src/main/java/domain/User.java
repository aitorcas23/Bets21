package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public abstract class User{
	private String name;
	private String surname;
	@Id
	private String username;
	private String password;
	//private int creditCard;
	private String email;
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private ArrayList<Message> messages; 
	//private double money;
	//@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	//public ArrayList<Movement> movementList;

	public User(String name, String surname, String username, String password, String email) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		//this.creditCard = creditCard;
		this.email = email;
		//this.admin = admin;
		//money = 0;
		//movementList = new ArrayList<Movement>();
		messages = new ArrayList<Message>();
	}
	
	public String getPassword() {
		return password;
	}

	/*public boolean isAdmin() {
		return admin;
	}*/
	
	public String getUsername() {
		return username;
	}

	public abstract boolean isAdmin();

	public Message sendMessage(User receiver, String text) {
		Message message = new Message(this, receiver, text);
		messages.add(message);
		return message;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}
	
	public ArrayList<Message> getMessages(){
		return messages;
	}
	
	@Override
	public String toString() {
		return username;
	}
	/*public void addMoney(double money) {
		this.money += money;
	}*/
	
	/*public int getCreditCard() {
		return creditCard;
	}*/
	
	/*public void addMovement(Movement movement) {
		movementList.add(movement);
	}*/
	
	/*public ArrayList<Movement> getMovements(){
		return movementList;
	}*/
	
	/*public Movement getMovement(int i) {
		return movementList.get(i);
	}*/
}
