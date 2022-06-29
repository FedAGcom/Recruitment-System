package com.fedag.recruitmentSystem.jira;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class MyJiraClient {

    @Value("${jira.name}")
    private final String username;

    @Value("${jira.password}")
    private final String password;

    @Value("${jira.URL}")
    private final String jiraUrl;
    private JiraRestClient restClient;

    public MyJiraClient() {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    public String getAllIssueByUserEmail() {
        SearchRestClient searchclient = restClient.getSearchClient();
        String s = searchclient.searchJql(
                        "assignee in ('larisavoytsekh65@mail.ru') order by created DESC")
                .claim().toString();
        System.out.println(s);//удалить
        return s;
    }
}


