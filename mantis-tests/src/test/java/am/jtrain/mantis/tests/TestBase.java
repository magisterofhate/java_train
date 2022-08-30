package am.jtrain.mantis.tests;

import am.jtrain.mantis.appmanager.ApplicationManager;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite (alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public boolean isIssueOpen(Integer issueId) throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soapURL")));
        IssueData issue = mc.mc_issue_get(app.getProperty("admin_login"), app.getProperty("admin_password"), BigInteger.valueOf(issueId));
        String status = issue.getStatus().getName();
        return !status.equals("resolved") && !status.equals("closed");
    }

    public void skipIfNotFixed(Integer issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Skipped due to issue " + issueId);
        }
    }

}
