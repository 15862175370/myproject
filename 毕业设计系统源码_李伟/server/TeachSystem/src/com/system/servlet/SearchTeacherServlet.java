package com.system.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stm.bean.MyTeacher;
import com.teachsystem.dao.Dao;

/**
 * Servlet implementation class SearchTeacherServlet
 */
@WebServlet("/SearchTeacherServlet")
public class SearchTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int tno=Integer.parseInt(request.getParameter("tno"));
		Dao dao=new Dao();
		List<MyTeacher>list=dao.getTeacherByno(tno);
		Gson g=new Gson();
		String json=g.toJson(list);
		response.getWriter().println(json);
	}

}
