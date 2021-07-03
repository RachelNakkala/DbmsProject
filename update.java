package dbms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class update extends Frame {
	Button UpdateStudentButton;
	TextField firstname, lastname, dob,gender,ph_no,caste,exam_hall_no,age;
	TextArea errorText;
	Connection connection;
	Choice choice;
	CheckboxGroup grp = new CheckboxGroup();
	Statement statement;
  public update()
  {
	 connectToDB();
	 buildGUI();
  }
  public void connectToDB()
  {
	 String dburl ="jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
     String username = "it19737030";
     String password ="vasavi";
     try 
     {
		connection=DriverManager.getConnection(dburl,username,password);
	 }
	 catch (SQLException e)
	 {
		 e.printStackTrace();
	 }
  }
  public void buildGUI()
  {
	//Handle Insert Account Button
	UpdateStudentButton = new Button("Update Student");
	addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent args0){
			System.exit(0);
		}
	});
	UpdateStudentButton.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		int flag = 1;
		if(ph_no.getText().length() != 10){
			errorText.append("\nPhone number should have 10 digits");
			flag = 0;
		}
		if(exam_hall_no.getText().length() != 10){
			errorText.append("\nExam hall ticket number should consist 10 digits");
			flag = 0;
		}
		if(flag==1) {
		try
		{
           String query="update student set firstname="+"'"+firstname.getText()+"',"+"lastname="+"'"+
		   lastname.getText() +"',"+"dob="+"'"+dob.getText() + "'," +"gender="+"'"+grp.getSelectedCheckbox().getLabel()+ "'," +
		   "ph_no="+"'"+ph_no.getText() + "'," +"caste="+"'"+choice.getSelectedItem() + "'," +"age="+"'"+age.getText() 
		   +"' "+"where exam_hall_no="+"'"+exam_hall_no.getText()+"'"+"";	   
		   Statement s =connection.createStatement();
		   int i = s.executeUpdate(query);
		   errorText.append("\nUpdated " + i + " row successfully");
		 }
		 catch (SQLException insertException)
		 {
		 displaySQLErrors(insertException);
		 }
		}
	}
	});
	firstname= new TextField(15);
	lastname = new TextField(15);
	dob = new TextField(15);
	caste= new TextField(15);
	ph_no= new TextField(15);
	choice=new Choice();
	choice.add("OC");
	choice.add("BC");
	choice.add("SC");
	choice.add("ST");
	exam_hall_no=new TextField(15);
	age=new TextField(15);
	errorText = new TextArea(10, 40);
	errorText.setEditable(false);
	Panel first = new Panel();
	first.setLayout(new GridLayout(7, 10));
	first.add(new Label("Student Firstname:"));
	first.add(firstname);
	first.add(new Label("Student LastName:"));
	first.add(lastname);
	first.add(new Label("Date of Birth:"));
	first.add(dob);
	first.add(new Label("Phone number:"));
	first.add(ph_no);
	first.add(new Label("Exam hall ticket no:"));
	first.add(exam_hall_no);
	first.add(new Label("Age:"));
	first.add(age);
	Checkbox cMale = new Checkbox("male", grp, false);
	Checkbox cFemale = new Checkbox("female", grp, true);
	first.setBounds(150,100,250,150);
	cMale.setBounds(280, 250, 50, 50);
	cFemale.setBounds(335, 250, 50, 50);
	choice.setBounds(280, 310, 100, 20);
	Panel second = new Panel(new GridLayout(4, 1));
	second.setBounds(150,250,200,200);
	second.add(new Label("Gender:"));
	second.add(new Label("Caste:"));
	second.add(UpdateStudentButton);
	Panel third = new Panel();
	third.add(errorText);
	third.setBounds(155,450,350,200);
	setLayout(null);
	add(first);
	add(cMale);
	add(cFemale);
	add(choice);
	add(second);
	add(third);
	setTitle("Updation");
	setSize(600, 650);
	setVisible(true);
 }
 private void displaySQLErrors(SQLException e)
 {
	 errorText.append("\nSQLException: " + e.getMessage() + "\n");
	 errorText.append("SQLState: " + e.getSQLState() + "\n");
	 errorText.append("VendorError: " + e.getErrorCode() + "\n");
 }
 public static void main(String[] args)throws Exception
{
		new update();
}
}

