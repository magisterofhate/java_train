package am.jtrain.addressbook.web.model;

import java.util.Objects;

public class ContactData {
    private Integer id;
    private final String firstname;
    private final String lastname;
    private  String middlename;
    private  String address;
    private  String home;
    private  String mobile;
    private  String phone2;
    private  String email;
    private  String email2;

    public ContactData(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public ContactData(Integer id, String firstname, String lastname, String middlename, String address, String home,
                       String mobile, String phone2, String email, String email2) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.phone2 = phone2;
        this.email = email;
        this.email2 = email2;
    }

    public String getFirstname() {

        return firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public String getMiddlename() {

        return middlename;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactData)) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
