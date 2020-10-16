//@Author: Maik De Leon Lopez
//@Date:   02/24/2019
//@Course: CSC 311-01


public class Books {
	private int year;
	private int pages;
	private String subject;
	private String title;
	private double rating;
	
	//Default Constructor Randomly Assigns Pages and Rating
	public Books(String bookTitle, int year, int subjectNum) {
		this.title = bookTitle;
		this.year = year;
		this.pages = 50+(int)(Math.random()*950);	//Random Number from 50 to 1000
		this.rating = Math.round((.1+(Math.random()*9.9))*10)/10.0; //Random Number from 0.1 to 10.0 Rounded to the Nearest Tenth
		switch(subjectNum) {	
		case 0:
			this.subject = "Programming";	//If the Subject Number is 0 then Assign Subject to Programming
			break;
		case 1:
			this.subject = "Data Structures";
			break;
		case 2:
			this.subject = "Algorithms";
			break;
		case 3:
			this.subject = "Operating Systems";
			break;
		case 4:
			this.subject = "Gaming";
			break;
		default:
			System.out.println("Error");	//If the Number is not 0-4 then it is an Error
			break;
		}
	}
	
	//Full User Input for the Book
	public Books(String title, int year, int pages, String subject, double rating) {
		this.title = title;
		this.year = year;
		this.pages = pages;
		this.subject = subject;
		this.rating = rating;
	}
	
	//My Formatting to Print Out the Book
	public String toString() {
		String s = ("Title: "+this.title+"\n		Year: "+this.year+" Pages: "+this.pages+"	Rating:"+this.rating+" Subject: "+this.subject+"\n");
		return s;
	}
	
	
	//These Next Methods Allow for Comparisons Without Direct Access to the Data
	
	public String getTitle() {
		return this.title;
	}
	public int getYear() {
		return this.year;
	}
	public int getPages() {
		return this.pages;
	}
	public String getSubject() {
		return this.subject;
	}
	public double getRating() {
		return this.rating;
	}
}
