package config;

import domain.Todo;
import services.TodoService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by ego on 05/04/15.
 */
@Singleton
@Startup
public class DBPopulator {

    @EJB
    private TodoService todoService;

    public DBPopulator(){
    }

    @PostConstruct
    public void populate(){
        todoService.create(new Todo("read after burning"));
        todoService.create(new Todo("run away"));
        todoService.create(new Todo("grab a knife"));

    }
}
