package app.db.fx;

public class Member {

	// columns (guest_id, name, phone, email)
	private int member_id = 0;
	private String vorname = null;
	private String nachname = null;
	private String email = null;
	
	// Leerer Konstruktor
	public Member() {
		
	}
	
	
	public Member(int member_id, String vorname, String nachname, String email) {
		this.member_id = member_id;
		this.vorname = vorname;
		this.nachname = nachname;
		this. email = email;
	}


	public int getMember_id() {
		return member_id;
	}


	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}


	public String getVorname() {
		return vorname;
	}


	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getNachname() {
		return nachname;
	}


	public void setNach(String nachname) {
		this.nachname = nachname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}//class
