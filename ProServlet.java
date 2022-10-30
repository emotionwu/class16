package com.bcu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcu.bean.Pro;

public class ProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Map<String,Pro> pros=new HashMap<>();
	public static String pro_id ;
	public static String pro_name;
	public static String pro_type;
	public static String pro_per ;
	public static String pro_dan ;
	public static String pro_num ;
    public ProServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		if("queryPros".equals(cmd)) {
			request.setAttribute("pros", pros);
			request.getRequestDispatcher("pro-list.jsp").forward(request, response);
		}
		else if("insertPro".equals(cmd)) {
			String pro_id = request.getParameter("pro_id");
			String pro_name = request.getParameter("pro_name");
			String pro_type = request.getParameter("pro_type");
			String pro_per = request.getParameter("pro_per");
			String pro_dan = request.getParameter("pro_dan");
			String pro_num = request.getParameter("pro_num");
			
			Pro pro = new Pro(pro_id,pro_name,pro_type,pro_per,pro_dan,pro_num);
			pros.put(pro_id, pro);
			
			response.sendRedirect("ProServlet?cmd=queryPros");
		}
		else if("getProUpdate".equals(cmd)) {
			String pro_id = request.getParameter("pro_id");
			Pro pro = pros.get(pro_id);
			
			request.setAttribute("pro", pro);
			request.getRequestDispatcher("pro-update.jsp").forward(request, response);
		}
		else if("updatePro".equals(cmd)) {
			String pro_id = request.getParameter("pro_id");
			String pro_name = request.getParameter("pro_name");
			String pro_type = request.getParameter("pro_type");
			String pro_per = request.getParameter("pro_per");
			String pro_dan = request.getParameter("pro_dan");
			String pro_num = request.getParameter("pro_num");
			
			Pro pro = pros.get(pro_id);
			pro.setPro_name(pro_name);
			pro.setPro_type(pro_type);
			pro.setPro_per(pro_per);
			pro.setPro_dan(pro_dan);
			pro.setPro_num(pro_num);
			
			System.out.println(pro_num);
			
			response.sendRedirect("ProServlet?cmd=queryPros");
		}
		else if("deletePro".equals(cmd)) {
			String pro_id = request.getParameter("pro_id");
			pros.remove(pro_id);
			
			response.sendRedirect("ProServlet?cmd=queryPros");
		}
		else if("gradePro".equals(cmd)) {
			String pro_id = request.getParameter("pro_id");
			String pro_num = request.getParameter("pro_num");
			Pro pro = pros.get(pro_id);
			pro.setPro_num(pro_num);
			request.getRequestDispatcher("write.jsp").forward(request, response);
		}
		else if("update1".equals(cmd)) {
			String pro_id = request.getParameter("pro_id");
			String pro_name = request.getParameter("pro_name");
			String pro_type = request.getParameter("pro_type");
			String pro_per = request.getParameter("pro_per");
			String pro_dan = request.getParameter("pro_dan");
			String pro_num = request.getParameter("pro_num");
			
			System.out.println(pro_id);
			System.out.println("============================");
			
			Pro pro = pros.get(pro_id);
			request.setAttribute("pro", pro);
			System.out.println(pro_id);
			pro.setPro_num(pro_num);
			request.getRequestDispatcher("grade.jsp").forward(request, response);
			//response.sendRedirect("ProServlet?cmd=queryPros");
		}
		/*else if("update2".equals(cmd)) {
			ProServlet proservlet = new ProServlet();
//			Map<String,Pro> pros=new HashMap<>();
//			pros=proservlet.pros;
			System.out.println("len ="+proservlet.pros.size());
			request.setAttribute("pro", proservlet.pros);
			String pro_id = request.getParameter("pro_id");
			String pro_num = request.getParameter("pro_num");
			
			Pro pro = pros.get(pro_id);
			pro.setPro_num(pro_num);
			
			System.out.println(pro_num);
			
			request.getRequestDispatcher("write.jsp").forward(request, response);
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
