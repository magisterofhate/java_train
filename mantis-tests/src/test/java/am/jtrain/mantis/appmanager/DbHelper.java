package am.jtrain.mantis.appmanager;

import am.jtrain.mantis.model.Issue;
import am.jtrain.mantis.model.Project;
import am.jtrain.mantis.model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(ApplicationManager app) {
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

    public Integer getProjectIdByName(String project_name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Project> result = session.createQuery(String.format("from Project where name = '%s'", project_name)).list();
        if (!(result.size() > 1)) {
            session.getTransaction().commit();
            session.close();
            return result.get(0).getId();
        }
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public Set<Issue> getIssuesByProjectId(Integer project_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Issue> result = session.createQuery(String.format("from Issue where project_id = %s", project_id)).list();
        Set<Issue> issues = new HashSet<>(result);
        session.getTransaction().commit();
        session.close();
        return issues;
    }
}
