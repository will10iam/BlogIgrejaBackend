package com.blogigreja.dao;

import com.blogigreja.model.Post;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
private static List<Post> posts = new ArrayList<>();
private static int idCounter = 1;

//Método para adicionar um novo post
public void adicionarPost(Post post) {
	post.setId(idCounter++);
	posts.add(post);
}

//Método para listar todos os posts
public List<Post> listarPosts(){
	return posts;
}

//Método para buscar um post por ID
public Post buscarPorId(int id) {
	return posts.stream().filter(post -> post.getId() == id).findFirst().orElse(null);
}

//Método para atualizar um post existente
public boolean atualizarPost(Post postAtualizado) {
	for(int i = 0; i < posts.size(); i++) {
		if(posts.get(i).getId() == postAtualizado.getId()) {
			posts.set(i, postAtualizado);
			return true;
		}
	}
	return false;
}

//Método para remover um post por ID
public boolean removerPost(int id) {
	return posts.removeIf(post -> post.getId() == id);
}
}
