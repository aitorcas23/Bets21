package domain;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Quote {
	private double value;
	private Question question;
	private String answer;
	@Id @GeneratedValue
	private int id;
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ArrayList<Bet> bets;
	
	public Quote(double value, String answer, Question question) {
		this.value=value;
		this.answer = answer;
		this.question = question;
		bets = new ArrayList<Bet>();
	}

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Quote(){
		super();
	}
	public String toString() {
		return answer + ": " + value;
	}
	public void addBet(Bet bet) {
		bets.add(bet);
	}

	public int getId() {
		return id;
	}

	public void removeBet(Bet bet) {
		bets.remove(bet);
	}

	public ArrayList<Bet> getBets() {
		return bets;
	}
	
	public String getAnswer() {
		return answer;
	}
}
