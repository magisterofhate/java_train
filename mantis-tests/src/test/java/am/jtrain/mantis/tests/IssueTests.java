package am.jtrain.mantis.tests;

import am.jtrain.mantis.model.Issue;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class IssueTests extends TestBase{

    @Test
    public void testIssuesFormDbOverSoap() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(3);
        String project_name = "Some Project";
        Set<Issue> issues_from_soap = app.soap().getAllIssuesOfProject(project_name);
        Set<Issue> issues_from_db = app.db().getIssuesByProjectId(app.db().getProjectIdByName(project_name));
        assertEquals(issues_from_db, issues_from_soap);
    }
}
