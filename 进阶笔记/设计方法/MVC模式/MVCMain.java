package Try_sig.Fac;

public class MVCMain {
	public static void main(String[] args) {
		Controller controller = new Controller(new Student("sb»ª", "man"), new StudentView());
		controller.showStudentInfo();
		controller.setStudentName("sbb»ª");
		controller.showStudentInfo();
		
	}
}
class Student{
	private String name;
	private String sex;
	public Student(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
class StudentView{
	
	public void printInfo(String name,String sex) {
		System.out.println("Student: "+name+" is "+sex);
	}
}
class Controller{
	private Student student;
	private StudentView studentView;
	public Controller(Student student, StudentView studentView) {
		this.student = student;
		this.studentView = studentView;
	}
	public void setStudentName(String name) {
		student.setName(name);
	}
	public void setStudentSex(String sex) {
		student.setSex(sex);
	}
	public void showStudentInfo() {
		studentView.printInfo(student.getName(), student.getSex());
	}
}