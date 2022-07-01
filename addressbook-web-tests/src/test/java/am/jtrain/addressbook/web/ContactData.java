package am.jtrain.addressbook.web;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String middlename;
    private final String address;
    private final String home;
    private final String mobile;
    private final String phone2;
    private final String email;
    private final String email2;

    public ContactData(String firstname, String lastname, String middlename, String address, String home,
                       String mobile, String phone2, String email, String email2) {
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
}
