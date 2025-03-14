package com.blogigreja.servlet;

import com.blogigreja.dao.PostDAO;
import com.blogigreja.model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Define a URL para acessar esse Servlet
@WebServlet("/posts")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO postDAO = new PostDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Obtém a lista de posts
		List<Post> posts = postDAO.listarPosts();
		
		out.println("<h1>Lista de Posts</h1>");
		for (Post post : posts) {
			out.println("<p><strong>" + post.getTitulo() + "</strong><br>" + post.getConteudo() + "</p>");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Obtém os dados do formulário
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		
		 // Cria um novo post
		Post novoPost = new Post(0, titulo, conteudo, conteudo, null);
		postDAO.adicionarPost(novoPost);
		
		// Redireciona para a listagem de posts
		response.sendRedirect("posts");
	}
}
   
