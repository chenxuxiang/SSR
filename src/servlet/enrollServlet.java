package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EnrollmentStatus;
import model.Section;
import model.Student;
import model.PlanOfStudy;
import service.PersonService;
import service.SectionService;

/**
 * Servlet implementation class enrollServlet
 */
@WebServlet("/enrollServlet")
public class enrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public enrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8") ;	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw=response.getWriter();
		String ssn=request.getParameter("ssn");
		String sectionNo=request.getParameter("sectionNo");
		SectionService sectionService=new SectionService();
		PersonService personService=new PersonService();
		try {
			Section section =sectionService.initSection(sectionNo);
			Student student=personService.initStudent(ssn);
			PlanOfStudy planOfStudy = new PlanOfStudy(student);
			boolean a = planOfStudy.VerifyPlan(section);
			if(a==false){
				EnrollmentStatus enrollmentStatus=section.enroll(student);
				pw.println(enrollmentStatus);
			}else{
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
