package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ego on 05/04/15.
 */
@Entity
@Table(name = "t_todo")
public class Todo implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;

    public Todo(){
    }

    public Todo(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
