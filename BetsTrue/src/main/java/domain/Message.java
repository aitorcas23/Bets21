package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
	@Id @GeneratedValue
	private int id;
	private User sender;
	private User receiver;
	private String text;
	
	public Message(User sender, User receiver, String text) {
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}
	
	@Override
	public String toString() {
		return sender + ": " + text;
	}

}
