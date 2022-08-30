package am.jtrain.mantis.appmanager;

import am.jtrain.mantis.model.Issue;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private final ApplicationManager app;
    private final MantisConnectPortType mc;

    public SoapHelper(ApplicationManager app) throws MalformedURLException, ServiceException {
        this.app = app;
        mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soapURL")));
    }

    public Set<Issue> getAllIssuesOfProject(String project_name) throws RemoteException {
        BigInteger project_id = mc.mc_project_get_id_from_name(app.getProperty("admin_login"), app.getProperty("admin_password"), project_name);
        IssueData[] issues_mantis = mc.mc_project_get_issues(app.getProperty("admin_login"), app.getProperty("admin_password"), project_id, null, BigInteger.valueOf(-1));

        return Arrays.stream(issues_mantis).map((i) -> new Issue().withId(i.getId().intValue())
                .withName(i.getSummary())).collect(Collectors.toSet());
    }

}
