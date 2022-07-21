package am.jtrain.addressbook.web.model;

import java.util.Objects;

public class GroupData {

    private Integer id;
    private final String name;
    private final String header;
    private final String footer;

    public GroupData(Integer id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

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

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupData)) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(id, groupData.id) && Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
