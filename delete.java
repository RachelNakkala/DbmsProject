package dbms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class delete extends Frame {
	Button deleteStudentButton;
	TextField firstname, lastname, dob,gender,ph_no,caste,exam_hall_no,age;
	TextArea errorText;
	Connection connection;
	Statement statement;
  public delete()
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
	deleteStudentButton = new Button("Delete Student");
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
		int flag=1;
		if(exam_hall_no.getText().length() < 10){
			errorText.append("\nExam hall ticket number should consist 10 digits");
			flag = 0;
		}
		if(flag==1) {
		try
		{
		   String query="Delete from student where firstname="+"'" +firstname.getText()+ "'" +" and lastname="+ "'" +lastname.getText()+"'" +"and exam_hall_no="+"'" +exam_hall_no.getText()+"'" +"";
		   Statement s =connection.createStatement();
		   int i = s.executeUpdate(query);
		   errorText.append("\nDeleted " + i + " row successfully");
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
	exam_hall_no=new TextField(15);
	errorText = new TextArea(10, 40);
	errorText.setEditable(false);
	Panel first = new Panel();
	first.setLayout(new GridLayout(3, 3));
	first.add(new Label("Student Firstname:"));
	first.add(firstname);
	first.add(new Label("Student LastName:"));
	first.add(lastname);
	first.add(new Label("Exam hall ticket no:"));
	first.add(exam_hall_no);
	first.setBounds(150,100,250,100);
	Panel second = new Panel(new GridLayout(4, 1));
	second.add(deleteStudentButton);
	second.setBounds(155,280,150,100);
	Panel third = new Panel();
	third.add(errorText);
	third.setBounds(155,380,350,200);
	setLayout(null);
	add(first);
	add(second);
	add(third);
	setTitle("Deletion");
	setSize(600, 600);
	setVisible(true);
 }
 private void displaySQLErrors(SQLException e)
 {
		e.printStackTrace();
 }
 public static void main(String[] args)throws Exception
{
		new delete();
}
}

