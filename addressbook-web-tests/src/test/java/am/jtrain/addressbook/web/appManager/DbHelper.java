package am.jtrain.addressbook.web.appManager;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groupsFromDb() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public GroupData groupDataByIdFromDb(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery(String.format("from GroupData where group_id = %s", id)).list();
        if (!(result.size() > 1)) {
            session.getTransaction().commit();
            session.close();
            return new GroupData().withId(id).withName(result.get(0).getName()).withHeader(result.get(0).getHeader())
                    .withFooter(result.get(0).getFooter());
        }
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public Contacts contactsFromDb() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public ContactData contactDataByIdFromDb(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery(String.format("from ContactData where id = %s", id)).list();
        if (!(result.size() > 1)) {
            session.getTransaction().commit();
            session.close();
            return new ContactData().withId(id).withLastname(result.get(0).getLastname())
                    .withFirstname(result.get(0).getFirstname()).withMiddlename(result.get(0).getMiddlename())
                    .withEmail(result.get(0).getEmail()).withMobile(result.get(0).getMobile());
        }
        session.getTransaction().commit();
        session.close();
        return null;
    }
}
