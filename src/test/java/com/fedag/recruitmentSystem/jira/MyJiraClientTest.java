package com.fedag.recruitmentSystem.jira;

import com.fedag.recruitmentSystem.enums.IssueStatus;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import org.assertj.core.util.Arrays;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MyJiraClientTest {

    String text;

    List<IssueDto> issueExpectList;

    static MyJiraClient myJiraClient;


    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {"key=sdfsdf, key=FIRST-1,key=FIRST,name=Done, name=In Progress,sdfsdfssfd" +
                        " key=FIRST-2,key=FIRST,fsdfsdfsd,name=Done, name=Done,ddfgdg",
                        Arrays.asList(new IssueDto[]{
                                new IssueDto("FIRST-1", "FIRST",
                                        IssueStatus.IN_PROCESS),
                                new IssueDto("FIRST-2", "FIRST",
                                        IssueStatus.DONE)})},
                {"key=FIRST-1,key=FIRST,name=name=In Progress," +
                        "key=FIRST-2,key=FIRST,name=name=In Progress,",
                        Arrays.asList(new IssueDto[]{
                                new IssueDto("FIRST-1", "FIRST",
                                        IssueStatus.IN_PROCESS),
                                new IssueDto("FIRST-2", "FIRST",
                                        IssueStatus.IN_PROCESS)})},
                {"AAAAAAAAAA,     key=FIRST-1,  name=  key=FIRST,name=name=To Do, name=" +
                        "key=FIRST-2,key=FIRST,name=name=To Do,name=",
                        Arrays.asList(new IssueDto[]{
                                new IssueDto("FIRST-1", "FIRST",
                                        IssueStatus.FOR_EXECUTION),
                                new IssueDto("FIRST-2", "FIRST",
                                        IssueStatus.FOR_EXECUTION)})},
                {"key=AAAAAAA,key=BBBBBBB,name=name=In Progress, name=" +
                        "key=AAAAAAA1,key=BBBBBBB1,name=name=In Progress,name=",
                        Arrays.asList(new IssueDto[]{
                                new IssueDto("AAAAAAA", "BBBBBBB",
                                        IssueStatus.IN_PROCESS),
                                new IssueDto("AAAAAAA1", "BBBBBBB1",
                                        IssueStatus.IN_PROCESS)})},
                {",,,,,,,,,,key=AAAAAAA,,,,,,,,,,,key=BBBBBBB,,,,,name=name=In Progress,,,,, name=" +
                        "///////key=AAAAAAA1,///////key=BBBBBBB1,/////name=name=In Progress,name=",
                        Arrays.asList(new IssueDto[]{
                                new IssueDto("AAAAAAA", "BBBBBBB",
                                        IssueStatus.IN_PROCESS),
                                new IssueDto("AAAAAAA1", "BBBBBBB1",
                                        IssueStatus.IN_PROCESS)})},
        });
    }

    public MyJiraClientTest(String text, List<IssueDto> issueExpectList) {
        this.text = text;
        this.issueExpectList = issueExpectList;
    }

    @BeforeClass
    public static void createMyJiraClient() {
        myJiraClient = new MyJiraClient();
    }

    @Test
    public void convertStringtoIssueDroTest() {
        List<IssueDto> issueResultList = myJiraClient.convertStringtoIssueDro(text);
        assertEquals(issueResultList.get(0).getIssueName(), issueExpectList.get(0).getIssueName());
        assertEquals(issueResultList.get(0).getProjectName(), issueExpectList.get(0).getProjectName());
        assertEquals(issueResultList.get(0).getIssueStatus(), issueExpectList.get(0).getIssueStatus());
        assertEquals(issueResultList.get(1).getIssueName(), issueExpectList.get(1).getIssueName());
        assertEquals(issueResultList.get(1).getProjectName(), issueExpectList.get(1).getProjectName());
        assertEquals(issueResultList.get(1).getIssueStatus(), issueExpectList.get(1).getIssueStatus());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void convertStringtoIssueDroTesttoNotStatusException() {
        myJiraClient.convertStringtoIssueDro("key=FIRST-1,key=FIRST,name=In Progresss,");
    }
}
