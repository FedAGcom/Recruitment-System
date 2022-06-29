package com.fedag.recruitmentSystem.jira;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.fedag.recruitmentSystem.enums.IssueStatus;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyJiraClient {

    private String username;

    private String password;

    private String jiraUrl;

    private JiraRestClient restClient;

    public MyJiraClient() {
        this.username="larisavoytsekh65@mail.ru";
        this.password="xO9cibtpeWsvCBxPlYvWE8E7";
        this.jiraUrl="https://barsuk.atlassian.net";
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    public List<IssueDto> getAllIssueByUserEmail() {
        SearchRestClient searchclient = restClient.getSearchClient();
        String resultSearch = searchclient.searchJql(
                        "assignee in ('wintmil7@gmail.com') order by created DESC")
                .claim().toString();
       List<IssueDto> issueList = convertStringtoIssueDro(resultSearch);
        System.out.println(resultSearch);//удалить
        return  issueList;
    }

    public List<IssueDto> convertStringtoIssueDro(String resultSearch) {
        List<IssueDto> issueList = new ArrayList<>();
        int currentSymbolOfText = 0;
        int endOfcurrentWord = 0;
        int i = 0;
        while (true) {
            IssueDto issueDto = new IssueDto();
            String searchword;
            while (true) {
                currentSymbolOfText = resultSearch.indexOf("key=", currentSymbolOfText);
                if (currentSymbolOfText == -1) {
                    break;
                }
                endOfcurrentWord = resultSearch.indexOf(",", currentSymbolOfText);

                searchword = resultSearch.substring(currentSymbolOfText + 4,endOfcurrentWord);
                char [] characters = searchword.toCharArray();
                if(Character.isLowerCase(characters[0])){
                    currentSymbolOfText+=4;
                    continue;
                }
                issueDto.setIssueName(searchword);
                currentSymbolOfText = endOfcurrentWord;
                break;
            }
            if (currentSymbolOfText == -1) {
                break;
            }





            while (true) {
                currentSymbolOfText = resultSearch.indexOf("key=", currentSymbolOfText);
                if (currentSymbolOfText == -1) {
                    break;
                }
                endOfcurrentWord = resultSearch.indexOf(",", currentSymbolOfText);

                searchword = resultSearch.substring(currentSymbolOfText + 4,endOfcurrentWord);
                char [] characters = searchword.toCharArray();
                if(Character.isLowerCase(characters[0])){
                    currentSymbolOfText+=4;
                    continue;
                }
                issueDto.setProjectName(searchword);
                currentSymbolOfText = endOfcurrentWord;
                break;
            }
            if (currentSymbolOfText == -1) {
                break;
            }






            currentSymbolOfText = resultSearch.indexOf("name=",currentSymbolOfText);
            if (currentSymbolOfText == -1){
                break;
            }
            currentSymbolOfText = resultSearch.indexOf("name=",currentSymbolOfText+5);
            endOfcurrentWord = resultSearch.indexOf(",",currentSymbolOfText);
            String issueStatus = resultSearch.substring(currentSymbolOfText+5, endOfcurrentWord);
            issueDto.setIssueStatus(switchIssueStatus(issueStatus));



            issueList.add(issueDto);
        }

        return issueList;
    }

    private IssueStatus switchIssueStatus(String issueStatus) {
        switch (issueStatus){
            case ("To Do"):
                    return IssueStatus.FOR_EXECUTION;
            case ("In Progress"):
                return IssueStatus.IN_PROCESS;
            case ("Done"):
                return IssueStatus.DONE;
            default:
                throw new ObjectNotFoundException("Status not found");
        }
    }
}


