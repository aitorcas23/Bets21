package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MultiBet {
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ArrayList<Bet> bets;
	private NotAdmin notAdmin;
	@Id @GeneratedValue
	private int id;
	private double minBet;
	private double betMoney;
	private Date date;
	
	public MultiBet(NotAdmin notAdmin) {
		this.notAdmin = notAdmin;
		bets = new ArrayList<Bet>();
		date = new Date();
	}

	public int getId() {
		return id;
	}

	public void createBet(Quote qu) {
		Bet bet = new Bet(this, qu);
		qu.addBet(bet);
		bets.add(bet);
	}

	public void gehituMinimoa(double betMinimum) {
		minBet+=betMinimum;
	}
	
	public double getMinBet() {
		return minBet;
	}
	
	public ArrayList<Bet> getBets(){
		return bets;
	}
	
	public NotAdmin getNotAdmin() {
		return notAdmin;
	}
	
	public double getBetMoney() {
		return betMoney;
	}
	
	public void setBetMoney(double betMoney) {
		this.betMoney = betMoney;
	}
	
	@Override
	public String toString() {
		return date + ":   " + betMoney + "€";
	}
}
