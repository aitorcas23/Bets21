package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movement {
	private double money;
	private int type;
	@Id @GeneratedValue
	private int id;
	/*
	0 = dirua sartu
	1 = dirua atera
	*/
	
	public Movement(double money, int type) {
		this.money = money;
		this.type = type;
	}
	
	public String toString() {
		String str = "";
		switch (type) {
		case 1: str = "Dirua sartu";
		break;
		case 2: str = "Dirua atera";
		break;
		case 3: str = "Apustua egin";
		break;
		case 4: str = "Apustua irabazi";
		break;
		case 5: str = "Apustua ezabatu";
		break;
		case 6: str = "Apustua kopiatu";
		break;
		}	
		if(type == 2 || type == 3 || type == 6) {
			return str + ": -" + money + "€";
		}else {
			return str + ": " + money + "€";
		}
	}
}
