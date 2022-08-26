package am.jtrain.addressbook.web.appmanager;

import am.jtrain.addressbook.web.model.ContactData;
import am.jtrain.addressbook.web.model.Contacts;
import am.jtrain.addressbook.web.model.GroupData;
import am.jtrain.addressbook.web.model.Groups;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<GroupData> result = session.createQuery( "from GroupData").list();
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

    public Integer rndGroupIdFromDb() {
        List <Integer> element_ids = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <GroupData> groups = session.createQuery( "from GroupData").list();

        for (GroupData group : groups) {
            Integer g_id = group.getId();
            element_ids.add(g_id);
        }

        Random random_method = new Random();
        int index = random_method.nextInt(element_ids.size());

        session.getTransaction().commit();
        session.close();

        return element_ids.get(index);
    }

    public Groups groupsWithContacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> groups = session.createQuery("from GroupData").list();

        List<GroupData> groups_with_contacts = groups.stream().filter(g -> g.getContacts().size() > 0).collect(Collectors.toList());

        session.getTransaction().commit();
        session.close();

        return new Groups(groups_with_contacts);
    }

    public Integer rndGroupWithContacts() {
        List <Integer> element_ids = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <GroupData> groups = session.createQuery( "from GroupData").list();

        List <GroupData> groups_with_contacts = groups.stream().filter(g -> g.getContacts().size() > 0).collect(Collectors.toList());

        for (GroupData group : groups_with_contacts) {
            Integer g_id = group.getId();
            element_ids.add(g_id);
        }

        Random random_method = new Random();
        int index = random_method.nextInt(element_ids.size());

        session.getTransaction().commit();
        session.close();

        return element_ids.get(index);
    }

    public Contacts contactsFromDb() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData").list();
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

    public List<Integer> contactGroupsFromDb(Integer c_id) {
        List <Integer> g_ids = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery(String.format("from ContactData where id = %s", c_id)).list();
        Set<GroupData> groups = result.get(0).getGroups();
        for (GroupData group : groups) {
            Integer g_id = group.getId();
            g_ids.add(g_id);
        }
        session.getTransaction().commit();
        session.close();
        return g_ids;
    }

    public List<Integer> groupContactsFromDb(Integer g_id) {
        List <Integer> c_ids = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery(String.format("from GroupData where id = %s", g_id)).list();
        Set<ContactData> contacts = result.get(0).getContacts();
        for (ContactData contact : contacts) {
            Integer c_id = contact.getId();
            c_ids.add(c_id);
        }
        session.getTransaction().commit();
        session.close();
        return c_ids;
    }

    public Boolean noFreeContacts() {
        List <Integer> c_ids = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> contacts = session.createQuery("from ContactData").list();
        for (ContactData contact : contacts) {
            if (contact.getGroups().equals(groupsFromDb())) {
                Integer c_id = contact.getId();
                c_ids.add(c_id);
            }
        }
        session.getTransaction().commit();
        session.close();
        return c_ids.size() == contactsFromDb().size();
    }

    public Integer rndFreeContact() {
        List <Integer> c_ids = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> contacts = session.createQuery("from ContactData").list();
        for (ContactData contact : contacts) {
            if (!contact.getGroups().equals(groupsFromDb())) {
                Integer c_id = contact.getId();
                c_ids.add(c_id);
            }
        }

        Random random_method = new Random();
        int index = random_method.nextInt(c_ids.size());

        session.getTransaction().commit();
        session.close();

        return c_ids.get(index);
    }

    public Integer groupIdForFreeContact(Integer free_contact_id) {
        List <Integer> g_ids = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> contacts = session.createQuery(String.format("from ContactData where id=%s", free_contact_id)).list();

        if (!(contacts.size() > 1)) {
            List<GroupData> contact_groups = contacts.get(0).getGroups().stream().map( (g) -> new GroupData().withId(g.getId())
                    .withName(g.getName()).withHeader(g.getHeader()).withFooter(g.getFooter())).collect(Collectors.toList());

            List<GroupData> free_groups_for_contact = new ArrayList<>(CollectionUtils.subtract(groupsFromDb(), contact_groups));

            for (GroupData group : free_groups_for_contact) {
                Integer g_id = group.getId();
                g_ids.add(g_id);
            }

            Random random_method = new Random();
            int index = random_method.nextInt(g_ids.size());

            session.getTransaction().commit();
            session.close();

            return g_ids.get(index);

        }
        session.getTransaction().commit();
        session.close();
        return null;
    }

}
