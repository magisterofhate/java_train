package am.jtrain.addressbook.web.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "group_list")
public class GroupData {

    @Id
    @Column (name = "group_id")
    private Integer id;
    @Column (name = "group_name")
    private String name;
    @Column (name = "group_header")
    @Type(type = "text")
    private String header;

    @Column (name = "group_footer")
    @Type(type = "text")
    private String footer;

    @ManyToMany (mappedBy = "groups")
    private Set<ContactData> contacts = new HashSet<>();

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public Integer getId() {
        return id;
    }

    public Contacts getContacts() {
        return new Contacts(contacts);
    }

    public GroupData withId(Integer id) {
        this.id = id;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(id, groupData.id) && Objects.equals(name, groupData.name) && Objects.equals(header, groupData.header) && Objects.equals(footer, groupData.footer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, footer);
    }
}
