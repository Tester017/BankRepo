package mav.bank.framework;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.Issue.FluentUpdate;

public class JiraServiceProvider {

	public JiraClient jira;
	public String project;

	public JiraServiceProvider(String jiraUrl, String username, String password, String project) {
		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public void createJiraTicket(String issueType, String summary, String description, String reporterName) {

		try {
			System.out.println(project+issueType+project);
			System.out.println(issueType);
			
			FluentCreate fleuntCreate = jira.createIssue(project, issueType);
			fleuntCreate.field(Field.SUMMARY, summary);
			fleuntCreate.field(Field.DESCRIPTION, description);
			Issue newIssue = fleuntCreate.execute();
			System.out.println("new issue created in jira with ID: " + newIssue);

		} catch (JiraException e) {
			e.printStackTrace();
		}

	}
	
	public void updateStatusToDone(String issue) {

		try {
			System.out.println("possible transitions:" );
			jira.getIssue(issue).getAllWorkLogs();
		
		} catch (JiraException e) {
			e.printStackTrace();
		}

	}



}
