package domain;

import java.util.ConcurrentModificationException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

import com.objectdb.o.InternalException;

import businessLogic.BLFacade;
import gui.MainGUI;

@Entity
public class Bet {
	@Id @GeneratedValue
	private int Id;
	private MultiBet multiBet;
	private Quote quote;
	
	public Bet(MultiBet multiBet, Quote quote) {
		this.multiBet = multiBet;
		this.quote = quote;
	}
	
	public Object getId() {
		return Id;
	}
	
	public String toString() {
		return quote.getQuestion().getEvent() + ": " + quote.getQuestion().getQuestion() + " " + quote.getAnswer();
	}

	public Quote getQuote() {
		return quote;
	}
	
	public Quote getErantzuna() {
		return quote;
	}
	
	public MultiBet getMultiBet() {
		return multiBet;
	}
}
