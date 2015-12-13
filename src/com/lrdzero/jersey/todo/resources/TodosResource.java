package com.lrdzero.jersey.todo.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.lrdzero.jersey.todo.dao.TodoDao;
import com.lrdzero.jersey.todo.model.Todo;

// Will map the resource to the URL todos
@Path("/todos")
public class TodosResource {

  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of todos to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Todo> getTodosBrowser() {
    List<Todo> todos = new ArrayList<Todo>();
    todos.addAll(TodoDao.instance.getModel().values());
    return todos;
  }

  // Return the list of todos for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<Todo> getTodos() {
    List<Todo> todos = new ArrayList<Todo>();
    todos.addAll(TodoDao.instance.getModel().values());
    
    return todos;
  }

  // retuns the number of todos
  // Use http://localhost:8080/com.vogella.jersey.todo/rest/todos/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = TodoDao.instance.getModel().size();
    return String.valueOf(count);
  }

  @POST  
  @Produces(MediaType.TEXT_HTML)
  	@Path("insert")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newTodo(@FormParam("id") String id,
      @FormParam("summary") String summary,
      @FormParam("description") String description,
      @Context HttpServletResponse servletResponse) throws IOException {
	  
    Todo todo = new Todo(id, summary);
    if (description != null) {
      todo.setDescription(description);
    }
    TodoDao.instance.getModel().put(id, todo);
    
    
    servletResponse.sendRedirect("../../create_todo.html");
    
	
  }
  @POST
  @Produces(MediaType.TEXT_HTML)
 	@Path("lista")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void lista(@FormParam("idbusca") String id,  
      @Context HttpServletResponse servletResponse) throws IOException {
	  String respuesta="";
	  boolean finaliza=false;
	  List<Todo> todos = new ArrayList<Todo>();
	  todos.addAll(TodoDao.instance.getModel().values());
	  for(int i=0;i<todos.size()&&!finaliza;i++){
		  if(todos.get(i).getId().equals(id)){
			  respuesta = todos.get(i).getDescription();
			  finaliza=true;
		  }
	  }
	  if(respuesta.equals("")){
		  servletResponse.sendRedirect("../../create_todo.html");
	  }
	  else{
		  servletResponse.sendRedirect(respuesta);
	  }
    
	
  }

  

  // Defines that the next path parameter after todos is
  // treated as a parameter and passed to the TodoResources
  // Allows to type http://localhost:8080/com.vogella.jersey.todo/rest/todos/1
  // 1 will be treaded as parameter todo and passed to TodoResource
  @Path("{todo}")
  public TodoResource getTodo(@PathParam("todo") String id) {
    return new TodoResource(uriInfo, request, id);
  }

}