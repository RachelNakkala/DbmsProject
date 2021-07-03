package dbms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class admission_delete extends Frame {
	Button deleteStudentButton;
	TextField firstname, lastname,college_name,date_of_enrollment;
	TextArea errorText;
	Choice choice;
	CheckboxGroup grp = new CheckboxGroup();
	Connection connection;
	Statement statement;
  public admission_delete()
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
	deleteStudentButton = new Button("Delete Admission Details");
	addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent args0){
			System.exit(0);
		}
	});
	deleteStudentButton.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			String query= "delete from admission where stu_firstname="+"'"+firstname.getText()+"' and stu_lastname="+"'"+lastname.getText()+"' and college_name="+
					"'"+college_name.getText()+"' and date_of_enrollment="+"'"+date_of_enrollment.getText()+"' and branch_name="+"'"+choice.getSelectedItem()+"'"+"";
		   Statement s =connection.createStatement();
		   int i = s.executeUpdate(query);
		   errorText.append("\nDeleted " + i + " row successfully");
		 }
		 catch (SQLException deleteException)
		 {
		 displaySQLErrors(deleteException);
		 }
		
	}
	});
	firstname= new TextField(15);
	lastname = new TextField(15);
	college_name= new TextField(15);
	date_of_enrollment= new TextField(15);
	choice=new Choice();
	choice.add("CSE");
	choice.add("ECE");
	choice.add("IT");
	choice.add("EEE");
	choice.add("MECH");
	choice.add("CIVIL");
	choice.add("BIOTECHNOLOGY");
	errorText = new TextArea(10, 40);
	errorText.setEditable(false);
	Panel first = new Panel();
	first.setLayout(new GridLayout(7, 10));
	first.add(new Label("Student Firstname:"));
	first.add(firstname);
	first.add(new Label("Student LastName:"));
	first.add(lastname);
	first.add(new Label("College name:"));
	first.add(college_name);
	first.add(new Label("Date of enrollment:"));
	first.add(date_of_enrollment);
	first.setBounds(150,120,260,150);
	choice.setBounds(280, 270, 100, 20);
	Panel second = new Panel(new GridLayout(4, 1));
	second.setBounds(150,250,200,200);
	second.add(new Label("Branch:"));
	second.add(deleteStudentButton);
	Panel third = new Panel();
	third.add(errorText);
	third.setBounds(155,450,350,200);
	setLayout(null);
	add(first);
	add(choice);
	add(second);
	add(third);
	setTitle("Deletion");
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
		new admission_delete();
}
}
