package businessLogic;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

//import domain.Booking;
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

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	public User isLoginCorrect(String username, String password);

	public Question getQuestion(int id);
	
	public void bet(NotAdmin notAdmin, Quote quote, MultiBet multiBet);
	
	public int makeBet(NotAdmin notAdmin, double money, MultiBet multiBet);

	public MultiBet createMultiBet(NotAdmin notAdmin);
	
	public MultiBet getMultiBet(MultiBet multiBet);

	public void removeMultiBet(MultiBet multiBet, boolean moneyBack);
	
	public int addMoney(double money, NotAdmin notAdmin, int creditCard);
	
	public int removeMoney(double money, NotAdmin notAdmin, int creditCard);
	
	public User getUserByUsername(String username);
	
	public void addErantzuna(Quote quote);
	
	public Event getEventById(int id);
	
	public boolean sendMessage(String uNameSender, String uNameReceiver, String text);
	
	public void deleteEventByNumber(Integer number);
	
	public void addQuote(double d, Question q, String string);
	
	public User register(String name, String surname, String username, String password, String email, int creditCard);
	
	public HashSet<User> getUsers(User user);

	public ArrayList<Message> getMessages(User user);


	void duplicateEvent(Integer valueAt, Date date);


	public NotAdmin getUserByUserName(String text);


	boolean followUser(NotAdmin user, String text, double parseInt);


	int addCart(NotAdmin notAdmin, Question q);
}
