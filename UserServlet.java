package com.bcu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcu.bean.Pro;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String,com.bcu.bean.User> users = new HashMap<>();
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		if("register".equals(cmd)) {
			String user_id = request.getParameter("user_id");
			String user_name = request.getParameter("user_name");
			String user_password = request.getParameter("user_password");
			String user_type = request.getParameter("user_type");
			
			com.bcu.bean.User user = new com.bcu.bean.User(user_id,user_name,user_password,user_type);
			users.put(user_name,user);
			
			request.setAttribute("operation","注册");
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}
		else if("login".equals(cmd)) {
			String user_name = request.getParameter("user_name");
			String user_password = request.getParameter("user_password");
			String user_type = request.getParameter("user_type");
			com.bcu.bean.User user = users.get(user_name);
			if(user != null && user.getUser_password().equals(user_password) && user.getUser_type().equals("管理员")) {
				request.getRequestDispatcher("pro-list.jsp").forward(request, response);
			}
			else if(user != null && user.getUser_password().equals(user_password) && user.getUser_type().equals("普通用户")) {
				ProServlet proservlet = new ProServlet();
//				Map<String,Pro> pros=new HashMap<>();
//				pros=proservlet.pros;
				System.out.println("len ="+proservlet.pros.size());
				request.setAttribute("pro", proservlet.pros);
				request.getRequestDispatcher("grade.jsp").forward(request, response);
				//response.sendRedirect("ProServlet?cmd=update1");
			}
			else {
				request.setAttribute("operation", "登录");
				request.getRequestDispatcher("failure.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
