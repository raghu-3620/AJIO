package control;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String un = request.getParameter("un");
		String pwd = request.getParameter("pwd");
		try {
			User u = DAO.isValidUser(un, pwd);
			if (u != null) {
				HttpSession session = request.getSession();
				session.setAttribute("udetails", u);
				String cat1 = request.getParameter("cat");
				dataList(request, response, cat1);
			} else {
				//String cat1 = request.getParameter("cat");
				//dataList(request, response, cat1);
				response.getWriter().append("Login Failed");

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	

	public void dataList(HttpServletRequest request, HttpServletResponse response, String str) {
		List<Item> list = null;
		try {
			if (str != null) {
				list = DAO.itemListByCat(str);
			} else {
				list = DAO.itemList();
			}
			Set<String> cat = DAO.catList();
			HttpSession session = request.getSession(false);
			session.setAttribute("cat", cat);
			session.setAttribute("values", list);
			
			response.sendRedirect("UserHome.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
