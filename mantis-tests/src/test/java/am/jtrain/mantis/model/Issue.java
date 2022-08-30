package am.jtrain.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table (name = "mantis_bug_table")
public class Issue {
    @Id
    @Column
    private int id;
    @Column
    private String summary;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", name='" + summary + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        Issue issue = (Issue) o;
        return id == issue.id && Objects.equals(summary, issue.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summary);
    }
}
