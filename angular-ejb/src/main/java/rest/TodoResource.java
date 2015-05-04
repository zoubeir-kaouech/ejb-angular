package rest;

import domain.Todo;
import services.TodoService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

/**
 * Created by ego on 05/04/15.
 */

@Path("todos")


public class TodoResource {

    @EJB
    private TodoService todoService;

    public TodoResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Todo todo) {
        if (todo.getId() != null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        Todo created = todoService.create(todo);
        return Response
                .created(
                        UriBuilder
                                .fromResource(TodoResource.class)
                                .path(String.valueOf(created.getId()))
                                .build()
                )
                .build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        Todo found = todoService.find(id);
        if (found == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(found).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Todo todo) {
        if (todo == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (!id.equals(todo.getId())) {
            return Response.status(Status.CONFLICT).build();
        }
        Todo found = todoService.find(id);
        if (found == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        todoService.update(todo);
        return Response.noContent().build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Todo toDelete = todoService.find(id);
        if (toDelete == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        todoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> findAll() {
        return todoService.findAll();
    }
}
