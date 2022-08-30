package am.jtrain.mantis.model;

import java.util.Objects;


public class Issue {

    private int id;
    private String subject;
    private String description;
    private String state_name;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withsubject(String name) {
        this.subject = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState_name() {
        return state_name;
    }

    public Issue withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", status='" + state_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        Issue issue = (Issue) o;
        return id == issue.id && Objects.equals(subject, issue.subject) && Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description);
    }
}
