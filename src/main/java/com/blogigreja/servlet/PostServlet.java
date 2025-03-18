package com.blogigreja.servlet;

import com.blogigreja.dao.PostDAO;
import com.blogigreja.model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Define a URL para acessar esse Servlet
@WebServlet("/posts")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Post> posts = new ArrayList<>();
	//private PostDAO postDAO = new PostDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.println("[");
		for (int i = 0; i < posts.size(); i++) {
			out.print(posts.get(i).toString());
			if(i < posts.size() - 1) {
				out.print(",");
			}
		}
		
		out.println("]");
		//Obtém a lista de posts
		//List<Post> posts = postDAO.listarPosts();
		
		//out.println(posts.toString());
		//for (Post post : posts) {
			//out.println("<p><strong>" + post.getTitulo() + "</strong><br>" + post.getConteudo() + "</p>");
		//}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Obtém os dados do formulário
		String titulo = request.getParameter("titulo");
		String conteudo = request.getParameter("conteudo");
		
		if(titulo == null || conteudo == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Título e conteúdo são obrigatórios");
			return;
		}
		
		 // Cria um novo post
		Post novoPost = new Post(posts.size() + 1, titulo, conteudo, "2025-03-18", "Autor");
		posts.add(novoPost);
		response.getWriter().println("Post criado com sucesso");
		//postDAO.adicionarPost(novoPost);
		
		// Redireciona para a listagem de posts
		response.sendRedirect("posts");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String novoConteudo = request.getParameter("conteudo");
		
		for (Post post : posts) {
			if(post.getTitulo().equals(titulo)) {
				post.setConteudo(novoConteudo);
				response.getWriter().println("Post atualizado com sucesso!");
				return;
			}
		}
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post não encontrado");
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		
		boolean removido = posts.removeIf(post -> post.getTitulo().equals(titulo));
		if(removido) {
			response.getWriter().println("Post excluído com sucesso");
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post não encontrado");
		}
	}
	
}
   
