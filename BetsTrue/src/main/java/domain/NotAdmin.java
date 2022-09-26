package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class NotAdmin extends User{
	private int creditCard;
	private double money;
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ArrayList<Movement> movementList;
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ArrayList<MultiBet> multiBets;
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ArrayList<Follow> follows;
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ArrayList<Question> cart;

	public NotAdmin(String name, String surname, String username, String password, int creditCard, String email) {
		super(name, surname, username, password, email);
		this.creditCard = creditCard;
		money = 0;
		movementList = new ArrayList<Movement>();
		multiBets = new ArrayList<MultiBet>();
		follows = new ArrayList<Follow>();
		cart = new ArrayList<Question>();
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	public void addMoney(double money) {
		this.money += money;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void removeMoney(double money) {
		this.money -= money;
	}

	public void addMovement(double money, int type) {
		Movement movement = new Movement(money, type);
		movementList.add(movement);
	}

	public ArrayList<Movement> getMovements() {
		return movementList;
	}

	public int getCreditCard() {
		return creditCard;
	}
	
	public MultiBet createMultiBet() {
		MultiBet mb = new MultiBet(this);
		multiBets.add(mb);
		return mb;
	}

	public ArrayList<MultiBet> getMultiBets() {
		return multiBets;
	}

	public void removeMultiBet(MultiBet multiBet) {
		multiBets.remove(multiBet);
	}
	
	public ArrayList<Follow> getFollows() {
		return follows;
	}
	
	public void addQuestion(Question question) {
		cart.add(question);
	}
	
	public ArrayList<Question> getCart(){
		return this.cart;
	}

	public Follow followUser(NotAdmin followed, double percentage) {
		Follow f = new Follow(this, followed, percentage);
		follows.add(f);
		return f;
	}
	
	public void addFollower(Follow follow) {
		follows.add(follow);
	}
}
