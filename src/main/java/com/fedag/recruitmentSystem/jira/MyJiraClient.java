package com.fedag.recruitmentSystem.jira;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.fedag.recruitmentSystem.enums.IssueStatus;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import org.springframework.stereotype.Component;

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
        this.username = "larisavoytsekh65@mail.ru";
        this.password = "xO9cibtpeWsvCBxPlYvWE8E7";
        this.jiraUrl = "https://barsuk.atlassian.net";
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    public List<IssueDto> getAllIssueByUserEmail(String email) {
        SearchRestClient searchclient = restClient.getSearchClient();
        String resultSearch = searchclient.searchJql(
                        "assignee in ('" + email + "') order by created DESC")
                .claim().toString();
        List<IssueDto> issueList = convertStringtoIssueDro(resultSearch);

        return issueList;
    }

    //Проходимся по всему ответу с jira и по ключам ищем значение
    public List<IssueDto> convertStringtoIssueDro(String resultSearch) {
        List<IssueDto> issueList = new ArrayList<>();
        //счетчик текущего символа
        int currentSymbolOfText = 0;
        //указатель на конец слова для IssueStatus
        int endOfСurrentWord;
        //Массив для вывода 2 элементов из метода searchKey
        Object[] objects;

        //бесконечный цикл пока не пройдемся по всему тексту
        //на каждой итерации цикла заполням поля одной задачи
        while (true) {
            IssueDto issueDto = new IssueDto();
            String searchword;

            //ищем название задачи
            objects = searchKey(currentSymbolOfText, resultSearch);
            //Проверка не закончился ли текст
            if (objects == null) {
                break;
            }
            searchword = (String) objects[0];
            currentSymbolOfText = (int) objects[1];
            issueDto.setIssueName(searchword);
            //Перемещаем указатель текущего символа на элемент после найденного слова
            currentSymbolOfText += searchword.length();


            //ищем название проекта
            objects = searchKey(currentSymbolOfText, resultSearch);
            if (objects == null) {
                break;
            }
            searchword = (String) objects[0];
            currentSymbolOfText = (int) objects[1];
            issueDto.setProjectName(searchword);
            //Перемещаем указатель текущего символа на элемент после найденного слова
            currentSymbolOfText += searchword.length();


            //ищем название проекта статус задачи
            //Пропускаем первый ключ name= так как он соответствует другому полю
            currentSymbolOfText = resultSearch.indexOf("name=", currentSymbolOfText);

            currentSymbolOfText = resultSearch.indexOf("name=", currentSymbolOfText + 5);
            endOfСurrentWord = resultSearch.indexOf(",", currentSymbolOfText);
            String issueStatus = resultSearch.substring(currentSymbolOfText + 5, endOfСurrentWord);
            //Присваеваем статусу результат преобразования статуса из текста к значению Enum'a IssueStatus
            issueDto.setIssueStatus(switchIssueStatus(issueStatus));

            //добавляем в List заполненную сущность IssueDto
            issueList.add(issueDto);
        }

        return issueList;
    }

    //Поиск названия задачи и проекта по ключу key=
    //Так как возвращаю 2 значения используется Object[]
    public Object[] searchKey(int currentSymbolOfText, String resultSearch) {
        String searchword;
        int endOfСurrentWord;

        //В бесконечном цикле ищу key= удовлетворяющий условию поиска
        while (true) {
            currentSymbolOfText = resultSearch.indexOf("key=", currentSymbolOfText);

            //Проверка не закончился ли текст
            if (currentSymbolOfText == -1) {
                return null;
            }
            endOfСurrentWord = resultSearch.indexOf(",", currentSymbolOfText);
            searchword = resultSearch.substring(currentSymbolOfText + 4, endOfСurrentWord);

            //Проверяю слово после key= на наличие первой маленькой буквы,
            // так как все задачи пишутся с большой буквы.
            // Если есть первая буква маленькая продолжаем поиск
            char[] characters = searchword.toCharArray();
            if (Character.isLowerCase(characters[0])) {
                currentSymbolOfText += 4;
            } else {
                Object[] objects = {searchword, currentSymbolOfText};
                return objects;
            }
        }
    }

    //switch case для определения поля IssueStatus в алгоритме convertStringtoIssueDro
    private IssueStatus switchIssueStatus(String issueStatus) {
        switch (issueStatus) {
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


