package com.lrdzero.jersey.todo.dao;

import java.util.HashMap;
import java.util.Map;

import com.lrdzero.jersey.todo.model.Todo;

public enum TodoDao {
  instance;
  
  private Map<String, Todo> contentProvider = new HashMap<>();
  
  private TodoDao() {
    
    Todo todo = new Todo("Alemania", "Leopard 2");
    todo.setDescription("http://data3.primeportal.net/tanks/peter_van_iren/leopard_2a4_nld/images/leopard_2a4_nld_02_of_15.jpg");
    contentProvider.put("1", todo);
    todo = new Todo("Rusia", "Armata");
    todo.setDescription("https://aw.my.com/us/sites/aw.my.com/files/styles/news_body_image_1/public/u183517/8jn9gki0yse.jpg");
    contentProvider.put("2", todo);
    todo = new Todo("Germany","Leopard 2");
    todo.setDescription("http://1.bp.blogspot.com/-cXZ57bzBeX4/TmCA8o0YMvI/AAAAAAAAG10/cbLrGPvfwmM/s1600/KMW_LEOPARD2_11.jpg");
    
  }
  public Map<String, Todo> getModel(){
    return contentProvider;
  }
  
}