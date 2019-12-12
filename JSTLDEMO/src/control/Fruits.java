package control;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Fruits
 */
public class Fruits extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Fruits() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		dataList(request, response, null);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String cat1 = request.getParameter("cat");
		dataList(request, response, cat1);
	}

	private void dataList(HttpServletRequest request, HttpServletResponse response, String str) {
		// TODO Auto-generated method stub
		List<Item> list = null;
		try {
			if (str != null) {
				list = new DAO().itemListByCat(str);
			} else {
				list = new DAO().itemList();
			}
			TreeSet<String> s = new DAO().catList();
			HttpSession session = request.getSession();
			session.setAttribute("cat", s);
			session.setAttribute("values", list);
			response.sendRedirect("Home.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
