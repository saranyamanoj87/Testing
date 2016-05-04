package com.jira;


import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;




public class JiraJersey {
	public static void main(String[] args) throws URISyntaxException {
        final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
        final URI jiraServerUri = new URI("https://jira.gdn-app.com/");
        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "saranya.manoj", "Subramanian@123");
        final NullProgressMonitor pm = new NullProgressMonitor();
        final Issue issue = restClient.getIssueClient().getIssue("MERCHANT-4362", pm);
 
        System.out.println(issue.getAffectedVersions());
        System.out.println(issue.getAssignee());
        System.out.println(issue.getStatus());
        System.out.println(issue.getDescription());
        System.out.println(issue.getLabels());
 
       
 
    }
 
   
 
}
	


/*RESTful architecture promotes stateless connection - that is there should be no notion of the user session.
Currently JRJC fully supports only Basic HTTP authentication (other means are coming soon), which means that you should not use it in public networks without encryption as your credentials more or less travel in the open text (just encoded with Base64). Thus use HTTPS to talk to your JIRA whenever possible. Use HTTP only in internal, private networks or for tests.
To talk to JIRA you need to obtain first a reference to com.atlassian.jira.rest.client.JiraRestClient object, which then allows you to get references to more specialized parts of the client stack responsible for things like: handling issues, users, issue metadata, projects, etc.
Classes implementing com.atlassian.jira.rest.client.JiraRestClientFactory are capable of giving you the reference to com.atlassian.jira.rest.client.JiraRestClient objects.
The current only implementation is Jersey-based one: com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory//
*/