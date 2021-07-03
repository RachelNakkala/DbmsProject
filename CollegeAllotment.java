package dbms;
import java.awt.*;
import java.awt.event.*;
public class CollegeAllotment extends Frame implements ActionListener{
	Label title;
	insert insertstudent;
	delete deletestudent;
	update updatestudent;
	college_insert collinsert;
	college_update collupdate;
	college_delete colldelete;
	admission_insert adinsert;
	admission_update adupdate;
	admission_delete addelete;
	CollegeAllotment()
	{
		title=new Label();
		title.setAlignment(Label.CENTER);
		title.setBounds(100,250,250,200);
		title.setText("COLLEGE ALLOTMENT SYSTEM");
		add(title);
		MenuBar mbar=new MenuBar();
		setMenuBar(mbar);
		Menu student=new Menu("Student");
		MenuItem item1,item2,item3;
		student.add(item1=new MenuItem("Insert Student"));
		student.add(item2=new MenuItem("Update Student"));
		student.add(item3=new MenuItem("Delete Student"));
		mbar.add(student);
		Menu college=new Menu("College");
		MenuItem item4,item5,item6;
		college.add(item4=new MenuItem("Insert College Details"));
		college.add(item5=new MenuItem("Update College Details"));
		college.add(item6=new MenuItem("Delete College Details"));
		mbar.add(college);
		Menu admission=new Menu("Admission");
		MenuItem item7,item8,item9;
		admission.add(item7=new MenuItem("Insert Admission Details"));
		admission.add(item8=new MenuItem("Update Admission Details"));
		admission.add(item9=new MenuItem("Delete Admission Details"));
		mbar.add(admission);
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
		item9.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent args0){
				System.exit(0);
			}
		});
		setTitle("CollegeAllotmentsystem");
		setBackground(null);
		setFont(new Font("Cambria",Font.BOLD,15));
		setLayout(null);
		setSize(500,600);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent a)
	{
		String str=a.getActionCommand();
		if(str.equals("Insert Student"))
		{
			new insert();
		}
		else if(str.equals("Update Student"))
		{
			new update();
		}
		else if(str.equals("Delete Student"))
		{
			new delete();
		}
		else if(str.equals("Insert College Details"))
		{
			new college_insert();
		}
		else if(str.equals("Update College Details"))
		{
			new college_update();
		}
		else if(str.equals("Delete College Details"))
		{
			new college_delete();
		}
		else if(str.equals("Insert Admission Details"))
		{
			new admission_insert();
		}
		else if(str.equals("Update Admission Details"))
		{
			new admission_update();
		}
		else if(str.equals("Delete Admission Details"))
		{
			new admission_delete();
		}
	}
	public static void main(String[] args)throws Exception
	{
			new CollegeAllotment();
			
	}
}
