package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends ElasticsearchRepository<Question, String> {
    @Override
    List<Question> findAll();

    @Override
    Page<Question> findAll(Pageable pageable);

    @Override
    <S extends Question> S save(S entity);

    @Override
    <S extends Question> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Question> findById(String s);

    @Override
    void deleteById(String s);


}
