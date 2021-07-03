package dbms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class college_update extends Frame {
	Button updateStudentButton;
	TextField college_name,college_id,fee,city_name,street_name,date_of_enrollment;
	TextArea errorText;
	Choice choice;
	CheckboxGroup grp = new CheckboxGroup();
	Connection connection;
	Statement statement;
  public college_update()
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
	updateStudentButton = new Button("Update Student");
	addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent args0){
			System.exit(0);
		}
	});
	updateStudentButton.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		   String query= "update college set fee="+"'"+fee.getText()+"' ,"+"city_name="+"'"+city_name.getText()+"',"+"street_name="+"'"+
		   street_name.getText()+"',"+"date_of_enrollment="+"'"+date_of_enrollment.getText()+"' "+"where college_name="+"'"+college_name.getText()+"' and college_id="+
				   "'"+college_id.getText()+"'"+"";
		   Statement s =connection.createStatement();
		   int i = s.executeUpdate(query);
		   errorText.append("\nUpdated " + i + " row successfully");
		 }
		 catch (SQLException insertException)
		 {
		 displaySQLErrors(insertException);
		 }
		
	}
	});
	college_name= new TextField(15);
	college_id = new TextField(15);
	fee = new TextField(15);
	city_name=new TextField(15);
	street_name= new TextField(15);
	date_of_enrollment=new TextField(15);
	errorText = new TextArea(10, 40);
	errorText.setEditable(false);
	Panel first = new Panel();
	first.setLayout(new GridLayout(7, 10));
	first.add(new Label("Student College name:"));
	first.add(college_name);
	first.add(new Label("Student College_id:"));
	first.add(college_id);
	first.add(new Label("College Fee:"));
	first.add(fee);
	first.add(new Label("City Name:"));
	first.add(city_name);
	first.add(new Label("Street Name:"));
	first.add(street_name);
	first.add(new Label("Date of enrollment:"));
	first.add(date_of_enrollment);
	first.setBounds(150,100,250,150);
	Panel second = new Panel(new GridLayout(4, 1));
	second.setBounds(150,250,200,200);
	second.add(updateStudentButton);
	Panel third = new Panel();
	third.add(errorText);
	third.setBounds(155,450,350,200);
	setLayout(null);
	add(first);
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
		new college_update();
}
}
