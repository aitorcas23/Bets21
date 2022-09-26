package businessLogic;
import java.util.ArrayList;
//hola
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.JFrame;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Quote;
import domain.User;
import domain.Bet;
import domain.Event;
import domain.Message;
import domain.Movement;
import domain.MultiBet;
import domain.NotAdmin;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import gui.LoginGUI;
import gui.MainGUIAdmin;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@Override
	public User isLoginCorrect(String username, String password) {
		dbManager.open(false);
		User u = dbManager.isLoginCorrect(username, password);
		dbManager.close();
		return u;
	}
	
	@Override
	public Question getQuestion(int id) {
		dbManager.open(false);
		Question q = dbManager.getQuestion(id);
		dbManager.close();
		return q;
	}

	@Override
	public void bet(NotAdmin notAdmin, Quote quote, MultiBet multiBet) {
		dbManager.open(false);
		dbManager.bet(notAdmin, quote, multiBet);
		dbManager.close();
	}

	@Override
	public int makeBet(NotAdmin notAdmin, double money, MultiBet multiBet) {
		dbManager.open(false);
		int i = dbManager.makeBet(notAdmin, money, multiBet);
		dbManager.close();
		return i;
	}

	@Override
	public MultiBet createMultiBet(NotAdmin notAdmin) {
		dbManager.open(false);
		MultiBet mb = dbManager.createMultiBet(notAdmin);
		dbManager.close();
		return mb;
	}

	@Override
	public MultiBet getMultiBet(MultiBet multiBet) {
		dbManager.open(false);
		MultiBet mb = dbManager.getMultiBet(multiBet);
		dbManager.close();
		return mb;
	}

	@Override
	public void removeMultiBet(MultiBet multiBet, boolean moneyBack) {
		dbManager.open(false);
		dbManager.removeMultiBet(multiBet, moneyBack);
		dbManager.close();
	}

	@Override
	public int addMoney(double money, NotAdmin notAdmin, int creditCard) {
		dbManager.open(false);
		int i = dbManager.addMoney(money, notAdmin, creditCard);
		dbManager.close();
		return i;
	}

	@Override
	public int removeMoney(double money, NotAdmin notAdmin, int creditCard) {
		dbManager.open(false);
		int i = dbManager.removeMoney(money, notAdmin, creditCard);
		dbManager.close();
		return i;
	}

	@Override
	public User getUserByUsername(String username) {
		dbManager.open(false);
		User u = dbManager.getUserByUsername(username);
		dbManager.close();
		return u;
	}

	@Override
	public void addErantzuna(Quote quote) {
		dbManager.open(false);
		dbManager.addErantzuna(quote);
		dbManager.close();
	}

	@Override
	public Event getEventById(int id) {
		dbManager.open(false);
		Event ev = dbManager.getEventById(id);
		dbManager.close();
		return ev;
	}

	@Override
	public boolean sendMessage(String uNameSender, String uNameReceiver, String text) {
		dbManager.open(false);
		boolean bool = dbManager.sendMessage(uNameSender, uNameReceiver, text);
		dbManager.close();
		return bool;
	}

	@Override
	public void deleteEventByNumber(Integer number) {
		dbManager.open(false);
		dbManager.deleteEventByNumber(number);
		dbManager.close();
	}

	@Override
	public void addQuote(double value, Question q, String feeName) {
		dbManager.open(false);
		dbManager.addQuote(feeName,q,value);
		dbManager.close();
	}

	@Override
	public User register(String name, String surname, String username, String password, String email, int creditCard) {
		dbManager.open(false);
    	User u = dbManager.register(name, surname, username, password,creditCard, email);
    	dbManager.close();
    	return u;
	}

	@Override
	public HashSet<User> getUsers(User user) {
		dbManager.open(false);
		HashSet<User> users = dbManager.getUsers(user);
		dbManager.close();
		return users;
	}

	@Override
	public ArrayList<Message> getMessages(User user) {
		dbManager.open(false);
		ArrayList<Message> messages = dbManager.getMessages(user);
		dbManager.close();
		return messages;
	}

	@Override
	public void duplicateEvent(Integer valueAt, Date date) {
		dbManager.open(false);
		dbManager.duplicateEvent(valueAt, date);
		dbManager.close();

	}

	@Override
	public NotAdmin getUserByUserName(String text) {
		dbManager.open(false);
		User user =dbManager.getUserByUsername(text);
		dbManager.close();
		return (NotAdmin) user;
	}

	@Override
	public boolean followUser(NotAdmin user, String text, double parseInt) {
		dbManager.open(false);
		boolean b = dbManager.followUser(user,text,parseInt);
		dbManager.close();
		return b;
		
	}
	
	public int addCart(NotAdmin notAdmin, Question q) {
		dbManager.open(false);
		int i = dbManager.addCart(notAdmin,q);
		dbManager.close();		
		return i;
	}
}

