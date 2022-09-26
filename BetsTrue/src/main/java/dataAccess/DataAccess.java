package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Bet;
import domain.Event;
import domain.Follow;
import domain.Message;
import domain.Movement;
import domain.MultiBet;
import domain.NotAdmin;
import domain.Question;
import domain.Quote;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		db.getTransaction().begin();
		
		try {
			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "AtlÃ©tico-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "AlavÃ©s-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "EspaÃ±ol-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-LeganÃ©s", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "AlavÃ©s-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "EspaÃ±ol-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "MÃ¡laga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-LeganÃ©s", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			Quote qu1 = new Quote(2,"Atletico",q1);
			Quote qu2 = new Quote(2,"Athletic",q1);
			Quote qu101 = new Quote(2,"Empate",q1);
			Quote qu3 = new Quote(2,"Atletico",q2);
			Quote qu4 = new Quote(2,"Athletic",q2);
			Quote qu102 = new Quote(2,"Empate",q2);
			Quote qu5 = new Quote(2,"Atletico",q3);
			Quote qu6 = new Quote(2,"Athletic",q3);
			Quote qu103 = new Quote(2,"Empate",q3);
			Quote qu7 = new Quote(2,"0",q4);
			Quote qu8 = new Quote(2,"1",q4);
			Quote qu9 = new Quote(2,"2",q4);
			Quote qu10 = new Quote(2,"3",q4);
			Quote qu11= new Quote(2,"4",q4);
			Quote qu12 = new Quote(2,"5",q4);
			Quote qu13 = new Quote(2,"More than 5",q4);
			Quote qu14 = new Quote(2,"Málaga",q5);
			Quote qu15 = new Quote(2,"Valencia",q5);
			Quote qu104 = new Quote(2,"Empate",q5);
			Quote qu16 = new Quote(2,"Sí",q6);
			Quote qu17 = new Quote(2,"No",q6);
			
			
			q1.addQuote(qu1);
			q1.addQuote(qu2);
			q1.addQuote(qu101);
			q2.addQuote(qu3);
			q2.addQuote(qu4);
			q2.addQuote(qu102);
			q3.addQuote(qu5);
			q3.addQuote(qu6);
			q3.addQuote(qu103);
			q4.addQuote(qu7);
			q4.addQuote(qu8);
			q4.addQuote(qu9);
			q4.addQuote(qu10);
			q4.addQuote(qu11);
			q4.addQuote(qu12);
			q4.addQuote(qu13);
			q5.addQuote(qu14);
			q5.addQuote(qu15);
			q5.addQuote(qu104);
			q6.addQuote(qu16);
			q6.addQuote(qu17);
			
			
			User u = new Admin("Aitor", "Castaño", "aitorcas", "1234", "aitorcas23@gmail.com");
			User u1 = new NotAdmin("Unax", "Labaka", "unax", "1234", 1, "humildad@gmail.com");
			NotAdmin u2 = new NotAdmin("a", "a", "a", "a", 1, "a");
			NotAdmin u3 = new NotAdmin("b", "b", "b", "b", 1, "b");
			
			db.persist(u1);
			db.persist(u);
			db.persist(u2);
			db.persist(u3);

			
			/*db.persist(qu1);
			db.persist(qu2);
			db.persist(qu3);
			db.persist(qu4);
			db.persist(qu5);
			db.persist(qu6);*/
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (RollbackException e){
			//System.out.println("sarakatunga");
		}
		/*
		db.getTransaction().begin();
		Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
		Event ev1=new Event(1, "AtlÃ©tico-Athletic", UtilDate.newDate(year,month,17));
		Question q1=ev1.addQuestion("Â¿QuiÃ©n ganarÃ¡ el partido?",1);
		Question q2=ev1.addQuestion("Â¿QuiÃ©n meterÃ¡ el primer gol?",2);
		db.persist(q1);
		db.persist(q2);
		db.persist(ev1);
		db.getTransaction().commit();
		*/
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	/*
	public String getPasswordOfUsername(String username) {
		User user = db.find(User.class, username);
		return user.getPassword();
	}
	*/
	
	public User isLoginCorrect(String username, String password) {
		User u = db.find(User.class, username);
		if(u != null) {
			if (!password.equals(u.getPassword())) {
				u = null;
			}
		}
		return u;
	}

	public Question getQuestion(int id) {
		return db.find(Question.class, id);
	}
	
	public void bet(NotAdmin notAdmin, Quote quote, MultiBet multiBet) {
		db.getTransaction().begin();
		MultiBet mb = db.find(MultiBet.class, multiBet.getId());
		Quote qu = db.find(Quote.class, quote.getId());
		mb.createBet(qu);
		mb.gehituMinimoa(qu.getQuestion().getBetMinimum());
		db.getTransaction().commit();
	}
	
	public int makeBet(NotAdmin notAdmin, double money, MultiBet multiBet) {
		NotAdmin u = db.find(NotAdmin.class, notAdmin.getUsername());
		if(multiBet.getBets().isEmpty()) {
			return 1;
		}else if(u.getMoney()< money) {
			return 2;
		}else if(money < multiBet.getMinBet()) {
			return 3;
		}else{
			db.getTransaction().begin();
			u.removeMoney(money);
			u.addMovement(money, 3);
			db.find(MultiBet.class, multiBet.getId()).setBetMoney(money);
			for(Follow f : u.getFollows()) {
				if(f.getFollowed().getUsername().equals(u.getUsername())) {
					System.out.println("magdalenas con chocolate");
					double fm = money * f.getPercentage()/100;
					NotAdmin uf = db.find(NotAdmin.class, f.getFollower().getUsername());
					if(uf.getMoney()>= fm && fm >= multiBet.getMinBet()) {
						uf.removeMoney(fm);
						uf.addMovement(fm, 6);
						MultiBet mb = uf.createMultiBet();
						mb.setBetMoney(fm);
						for(Bet b : multiBet.getBets()) {
							Quote qu = db.find(Quote.class, b.getQuote().getId());
							mb.createBet(qu);
							mb.gehituMinimoa(qu.getQuestion().getBetMinimum());
						}
					}
				}
			}
			db.getTransaction().commit();
			return 0;
		}
	}

	public MultiBet createMultiBet(NotAdmin notAdmin) {
		db.getTransaction().begin();
		MultiBet mb = db.find(NotAdmin.class, notAdmin.getUsername()).createMultiBet();
		db.getTransaction().commit();
		return mb;
	}
	
	public MultiBet getMultiBet(MultiBet multiBet) {
		return db.find(MultiBet.class, multiBet.getId());
	}

	public void removeMultiBet(MultiBet multiBet, boolean moneyBack) {
		db.getTransaction().begin();
		multiBet = db.find(MultiBet.class, multiBet.getId());
		NotAdmin u = db.find(NotAdmin.class, multiBet.getNotAdmin().getUsername());
		u.removeMultiBet(multiBet);
		for(Bet b : multiBet.getBets()) {
			db.find(Quote.class, b.getQuote().getId()).removeBet(b);
		}
		if(moneyBack) {
			u.addMoney(multiBet.getBetMoney());
			u.addMovement(multiBet.getBetMoney(), 5);
		}
		db.getTransaction().commit();
	}
	
	public int addMoney(double money, NotAdmin notAdmin, int creditCard) {
		
		NotAdmin u = db.find(NotAdmin.class, notAdmin.getUsername());
		if(creditCard != u.getCreditCard()) {
			return 1;
		}else if(money <0){
			return 2;
		}else {
			db.getTransaction().begin();
			u.addMoney(money);
			u.addMovement(money, 1);
			db.getTransaction().commit();
			return 0;
		}
	}
	
	public int removeMoney(double money, NotAdmin notAdmin, int creditCard) {
		NotAdmin u = db.find(NotAdmin.class, notAdmin.getUsername());
		if(creditCard != u.getCreditCard()) {
			return 1;
		}else if(money < 0) {
			return 2;
		}else if(money > u.getMoney()) {
			return 3;
		}else {
			db.getTransaction().begin();
			u.removeMoney(money);
			u.addMovement(money, 2);
			db.getTransaction().commit();
			return 0;
		}
	}
	
	public User getUserByUsername(String username) {
		return db.find(User.class, username);
	}
	
	public void addErantzuna(Quote qu) {
		db.getTransaction().begin();
		db.find(Question.class,qu.getQuestion().getQuestionNumber()).addErantzuna(qu);
		HashSet<MultiBet> mblist = new HashSet<MultiBet>();
		Quote quote = db.find(Quote.class, qu.getId());
		Question question = db.find(Question.class, quote.getQuestion().getQuestionNumber());
		for(Quote quo : question.getQuotes()) {
			for(Bet b : quo.getBets()) {
				MultiBet mb = b.getMultiBet();
				if(!mblist.contains(mb)) {
					mblist.add(mb);
				}
			}
		}
		for(MultiBet mb : mblist) {
			MultiBet mb2 = db.find(MultiBet.class, mb.getId());
			boolean win = true;
			double value = 1;
			boolean ended = true;
			for(Bet b : mb2.getBets()) {
				Question q = db.find(Question.class, b.getQuote().getQuestion().getQuestionNumber());
				Quote result = q.getResult();
				if(result == null) {
					ended = false;
					win = false;
				}else if(result.getId() != b.getQuote().getId()) {
					win = false;
				}
				value = value * b.getQuote().getValue();
			}
			NotAdmin u = db.find(NotAdmin.class, mb2.getNotAdmin().getUsername());
			if(win) {
				double money = mb2.getBetMoney();
				u.addMoney(money * value);
				u.addMovement(money * value, 4);
			}
			if(ended || !win) {
				mb = db.find(MultiBet.class, mb.getId());
				u.removeMultiBet(mb);
				for(Bet b : mb.getBets()) {
					db.find(Quote.class, b.getQuote().getId()).removeBet(b);
				}
			}
		}
		db.getTransaction().commit();
	}
	
	public Event getEventById(int id) {
		return db.find(Event.class, id);
	}
	
	public boolean sendMessage(String uNameSender, String uNameReceiver, String text) {
		User uSender = db.find(User.class, uNameSender);
		User uReceiver = db.find(User.class, uNameReceiver);
		if(uReceiver == null) {
			return false;
		}
		db.getTransaction().begin();
		Message message = uSender.sendMessage(uReceiver, text);
		uReceiver.addMessage(message);
		db.getTransaction().commit();
		return true;
	}
	
	public void deleteEventByNumber(Integer number) {
		db.getTransaction().begin();
		HashSet<MultiBet> removeList = new HashSet<MultiBet>();
		Event ev = db.find(Event.class, number);
		
		for(Question q: ev.getQuestions()) {
			for(Quote qu: q.getQuotes()) {
				for(Bet b: qu.getBets()) {
					MultiBet aa = b.getMultiBet();
					double money = aa.getBetMoney();
					NotAdmin u = aa.getNotAdmin();
					String uName = u.getUsername();
					u = db.find(NotAdmin.class, uName);
					u.addMoney(money);
					u.addMovement(money, 5);
					u.removeMultiBet(aa);
					removeList.add(aa);
				}
			}
		}
		db.remove(ev);
		
		for(MultiBet aa2: removeList) {
			db.remove(aa2);
		}
		db.getTransaction().commit();
		
	}
	
	public User register(String name, String surname, String username, String password,int creditCard, String email) {
		User u = db.find(User.class, username);
		if(u == null) {
			db.getTransaction().begin();
			u = new NotAdmin(name, surname, username, password,creditCard, email);
			db.persist(u);
			db.getTransaction().commit();
			return u;
		}else {
			return null;
		}
	}
	
	public void addQuote(String FeeName, Question q, double value) {
		int qn = q.getQuestionNumber();
		db.getTransaction().begin();
		q = db.find(Question.class, qn);
		q.addQuote(value, FeeName);
		db.getTransaction().commit();
	}
	
	public HashSet<User> getUsers(User user){
		HashSet<User> users = new HashSet<User>();
		for(Message m : db.find(User.class, user.getUsername()).getMessages()) {
			User s = m.getSender();
			User r = m.getReceiver();
			if(!s.getUsername().equals(user.getUsername()) && !users.contains(s)) {
				users.add(s);
			}
			if(!r.getUsername().equals(user.getUsername()) && !users.contains(r)) {
				users.add(r);
			}
		}
		return users;
	}

	public ArrayList<Message> getMessages(User user) {
		return db.find(User.class, user.getUsername()).getMessages();
	}

	public void duplicateEvent(Integer valueAt, Date date) {
		db.getTransaction().begin();
		Event e= db.find(Event.class, valueAt);
		String d=e.getDescription();
		Event e2= new Event(d, date);
		for(Question q : e.getQuestions()) {
			Question q2=	e2.addQuestion(q.getQuestion(),q.getBetMinimum());
			for(Quote k: q.getQuotes()) {
				q2.addQuote(k.getValue(),k.getAnswer());
			}

		}

		db.persist(e2);
		db.getTransaction().commit();
	}

	public boolean followUser(NotAdmin user, String text, double parseInt) {
		db.getTransaction().begin();
		boolean b;
		NotAdmin u2=db.find(NotAdmin.class, text);
		if(u2 == null) {
			b = false;
		}else {
			NotAdmin u = db.find(NotAdmin.class, user.getUsername());
			Follow f = u.followUser(u2, parseInt);
			u2.addFollower(f);
			b = true;
		}
		db.getTransaction().commit();
		return b;
		
		
	}

	public int addCart(NotAdmin notAdmin, Question q) {
		NotAdmin uf = db.find(NotAdmin.class, notAdmin.getUsername());
		if(q.getQuestion()==null) {
			return 1;
		}else{
			db.getTransaction().begin();
			Question question = db.find(Question.class, q.getQuestionNumber());
			uf.addQuestion(question);
			db.getTransaction().commit();
		}
		return 0;
	}
}
