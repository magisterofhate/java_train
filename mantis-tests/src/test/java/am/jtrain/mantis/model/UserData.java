package am.jtrain.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "mantis_user_table")
public class UserData {

    @Id
    @Column
    private Integer id;

    @Column
    private String username;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserData withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }
}
