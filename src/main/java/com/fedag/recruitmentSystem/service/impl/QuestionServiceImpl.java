package com.fedag.recruitmentSystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedag.recruitmentSystem.model.Question;
import com.fedag.recruitmentSystem.service.QuestionService;
import lombok.SneakyThrows;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final static String INDEX_NAME = "question";
    private final ObjectMapper mapper = new ObjectMapper();
    private final RestHighLevelClient esClient;

    public QuestionServiceImpl(@Qualifier("client") RestHighLevelClient esClient) {
        this.esClient = esClient;
        //initDB();
    }

    @Override
    public void addQuestion(String id, String title, String question, String answer, String correct) {
        Question q = new Question();
        q.setId(id);
        q.setTitle(title);
        q.setQuestion(question);
        q.setAnswer(answer);
        q.setCorrect(correct);

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id(id);
        try {
            indexRequest.source(mapper.writeValueAsString(q), XContentType.JSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            esClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestion(String id, String title, String question, String answer, String correct) {
        // The code will be later...
    }

    @Override
    public List<Question> searchQuestionsByTitle(String searchString) {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title", searchString));

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Question> questions = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Question q = new Question();
            q.setId(hit.getId());
            q.setTitle((String) sourceAsMap.get("title"));
            q.setQuestion((String) sourceAsMap.get("question"));
            q.setAnswer((String) sourceAsMap.get("answer"));
            q.setCorrect((String) sourceAsMap.get("correct"));
            questions.add(q);
        }
        return questions;
    }

    @Override
    public void deleteQuestionById(String id) {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX_NAME);
        deleteRequest.id(id);
        try {
            DeleteResponse deleteResponse = esClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void initDB(){
        FileReader fileReader = new FileReader("src\\main\\resources\\questions.json");
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

        JSONArray questions = (JSONArray) jsonObject.get("questions");

        Iterator i = questions.iterator();

        while (i.hasNext()){
            JSONObject innerObj = (JSONObject) i.next();
            JSONObject title = (JSONObject) innerObj.get("title");
            JSONObject question = (JSONObject) innerObj.get("question");
            JSONObject answer = (JSONObject) innerObj.get("answer");
            JSONObject correct = (JSONObject) innerObj.get("correct");
            String id = UUID.randomUUID().toString();

            addQuestion(id, title.get("type").toString(), question.get("type").toString(), answer.get("type").toString(), correct.get("type").toString());
        }
    }
}

