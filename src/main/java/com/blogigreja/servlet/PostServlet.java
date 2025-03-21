package com.blogigreja.servlet;

import com.blogigreja.dao.PostDAO;
import com.blogigreja.model.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//Define a URL para acessar esse Servlet
@WebServlet("/posts")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Post> posts = new ArrayList<>();
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	//private PostDAO postDAO = new PostDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String json = gson.toJson(posts);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		//out.println("[");
		//for (int i = 0; i < posts.size(); i++) {
			//out.print(posts.get(i).toString());
			//if(i < posts.size() - 1) {
				//out.print(",");
			//}
		//}
		
		
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
		
		System.out.println("Título recebido: " + titulo);
		System.out.println("Conteúdo recebido: " + conteudo);
		
		if(titulo == null || conteudo == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Título e conteúdo são obrigatórios");
			return;
		}
		
		 // Cria um novo post
		Post novoPost = new Post(posts.size() + 1, titulo, conteudo, "William", "2025-03-18");
		posts.add(novoPost);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(gson.toJson(novoPost));
		response.getWriter().flush();
		//postDAO.adicionarPost(novoPost);
		
		// Redireciona para a listagem de posts
		//response.sendRedirect("posts");
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Lê o corpo da requisição
	    BufferedReader reader = request.getReader();
	    Gson gson = new Gson();
	    Post postAtualizado = gson.fromJson(reader, Post.class);

	    // Obtém o ID da URL
	    int id = Integer.parseInt(request.getParameter("id"));

	    for (Post post : posts) {
	        if (post.getId() == id) {
	            // Atualiza os campos corretamente
	            post.setTitulo(postAtualizado.getTitulo());
	            post.setConteudo(postAtualizado.getConteudo());

	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().print(gson.toJson(post));
	            response.getWriter().flush();
	            return;
	        }
	    }
	    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post não encontrado");
	}

	
	/*
	 * protected void doPut(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String idParam =
	 * request.getParameter("id"); System.out.println("ID recebido na requisição: "
	 * + idParam);
	 * 
	 * if(idParam == null || idParam.isEmpty()) {
	 * response.sendError(HttpServletResponse.SC_BAD_REQUEST,
	 * "ID do post é obrigatório"); return; }
	 * 
	 * try { int id = Integer.parseInt(idParam);
	 * System.out.println("ID convertido para inteiro: " + id); } catch
	 * (NumberFormatException e) {
	 * response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
	 * return; }
	 * 
	 * int id = Integer.parseInt(idParam); String novoTitulo =
	 * request.getParameter("titulo"); String novoConteudo =
	 * request.getParameter("conteudo");
	 * 
	 * System.out.println("ID recebido: " + id);
	 * System.out.println("Título recebido: " + novoTitulo);
	 * System.out.println("Conteúdo recebido: " + novoConteudo);
	 * 
	 * 
	 * boolean encontrado = false; for (Post post : posts) { if(post.getId() == id)
	 * { post.setTitulo(novoTitulo); post.setConteudo(novoConteudo); encontrado =
	 * true;
	 * 
	 * response.setContentType("application/json");
	 * response.setCharacterEncoding("UTF-8");
	 * response.getWriter().print(gson.toJson(post)); response.getWriter().flush();
	 * return; } } if(!encontrado) {
	 * response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post não encontrado");
	 * } }
	 */
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Obtém o ID da URL
	    String idParam = request.getParameter("id");

	    if (idParam == null || idParam.isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID é obrigatório para excluir um post.");
	        return;
	    }

	    int id = Integer.parseInt(idParam);

	    Iterator<Post> iterator = posts.iterator();
	    while (iterator.hasNext()) {
	        Post post = iterator.next();
	        if (post.getId() == id) {
	            iterator.remove(); // Remove o post da lista
	            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 - Sem conteúdo (deletado com sucesso)
	            return;
	        }
	    }

	    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post não encontrado.");
	}

	
}
   
