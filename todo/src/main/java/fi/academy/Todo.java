package fi.academy;

import java.util.Date;

public class Todo {
    private int id;
    private String task;
    private Date due;

    public Todo() {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Todo(int id, String task, Date due) {
        this.id = id;
        this.task = task;
        this.due = due;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", due=" + due +
                '}';
    }

    public void setDue(java.sql.Date due) {
    }
}
