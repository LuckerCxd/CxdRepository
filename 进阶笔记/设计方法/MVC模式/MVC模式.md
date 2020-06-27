MVC模式：开发模式

    Controller:	     servlet
    	1.获取用户输入
    	2.调用模型Model
    	3.将Model传来的数据交给视图展示
    
    Model:			javaBean
    	完成具体的业务操作，封装对象，增删改查,将数据返回给Controller
    
    View:			jsp
    	展示数据	




	model,view,controller
	model被隐藏，显示的是view，而具体操作是通过controller进行的

	1.定义model产品类
	2.定义view视图类，仅作为显示方法
	3.定义controller控制器类，内有model,view成员变量，
		在执行操作时，使用的是成员变量具备的方法(显示或是对model修改)
	4.客户端通过对controller进行实例化时，将model，view绑定到controller
		并使用controller进行操作
	
	model：封装controller获取的数据，实现对数据的业务操作，并交由controller
	view: 视图展示
	controller:获取输入将数据交给model,将model返回的数据交给view显示


1.定义model产品类

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
2.定义view视图类，仅作为显示方法

	class StudentView{
		
		public void printInfo(String name,String sex) {
			System.out.println("Student: "+name+" is "+sex);
		}
	}

3.定义controller控制器类，内有model,view成员变量，
	在执行操作时，使用的是成员变量具备的方法(显示或是对model修改)	
	
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

4.客户端通过对controller进行实例化时，将model，view绑定到controller
	并使用controller进行操作

	public static void main(String[] args) {
		Controller controller = new Controller(new Student("sb华", "man"), new StudentView());
		controller.showStudentInfo();
		controller.setStudentName("sbb华");
		controller.showStudentInfo();
	}



表示层(WEB层) -> 业务逻辑层(service层) -> 数据访问层(dao层) -> DB(数据库)
	
	
	表示层：	接收业务参数，封装数据，调用业务逻辑层完成处理，由request共享数据，
			并转发到jsp页面完成显示，Web层不必持有service对象
		控制器：Servlet
		视图：jsp
	业务逻辑层：	使用数据访问层的简单方法，组合为复杂的功能，将数据返回给表示层
				service层可以持有service对象
	数据访问层： 定义对数据库基本的增删改查操作		