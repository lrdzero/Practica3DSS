package com.lrdzero.jersey.todo.dao;

import java.util.HashMap;
import java.util.Map;

import com.lrdzero.jersey.todo.model.Todo;

public enum TodoDao {
  instance;
  
  private Map<String, Todo> contentProvider = new HashMap<>();
  
  private TodoDao() {
    
    Todo todo = new Todo("1", "Learn REST");
    todo.setDescription("Read http://www.vogella.com/tutorials/REST/article.html");
    contentProvider.put("1", todo);
    todo = new Todo("2", "Do something");
    todo.setDescription("Read complete http://www.vogella.com");
    contentProvider.put("2", todo);
    todo = new Todo("3","Leopard 2");
    todo.setDescription("http://1.bp.blogspot.com/-cXZ57bzBeX4/TmCA8o0YMvI/AAAAAAAAG10/cbLrGPvfwmM/s1600/KMW_LEOPARD2_11.jpg");
    
  }
  public Map<String, Todo> getModel(){
    return contentProvider;
  }
  
}