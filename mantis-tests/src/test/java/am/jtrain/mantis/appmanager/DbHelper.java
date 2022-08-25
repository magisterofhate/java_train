package am.jtrain.mantis.appmanager;

import am.jtrain.mantis.model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class DbHelper {

    private final ApplicationManager app;
    private final SessionFactory sessionFactory;

    public DbHelper(ApplicationManager app) {
        this.app = app;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public UserData userDataByIdFromDb(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery(String.format("from UserData where id = %s", id)).list();
        if (!(result.size() > 1)) {
            session.getTransaction().commit();
            session.close();
            return new UserData().withId(result.get(0).getId()).withUsername(result.get(0).getUsername());
        }
        session.getTransaction().commit();
        session.close();
        return null;
    }
}
