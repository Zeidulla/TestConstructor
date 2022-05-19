package com.testsystem.TestConstructor.models;

import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;
import javax.xml.transform.Result;
import java.util.List;

@Entity
public class Test { /* сделаю пометку */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean publish; /* сделаю пометку */
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List <TypePatternQuestions.Question> question; /* нужно проверить! */
    @OneToMany(cascade = CascadeType.ALL)
    private List <Result> result;
    public Test (User user, String name, String description){
        this.user = user;
        this.name = name;
        this.description = description;
        this.publish = false;/* сделаю пометку */
    }
    public Test(){}
    public Long getId(){/* сделаю пометку */
        return id;
    }
    public void setId(Long id) {/* сделаю пометку */
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<TypePatternQuestions.Question> getQuestion() {/* нужно проверить! */
        return question;
    }
    public void setQuestion(List<TypePatternQuestions.Question> question) {/* нужно проверить! */
        this.question = question;
    }
    public void addQuest (TypePatternQuestions.Question question){/* нужно проверить! */
        this.question.add(question);
    }
    public String getDescription(){
        return description;
    }
    public void  setDescription(String description) {
        this.description = description;
    }
    public User getUser() {
        return user;
    }
    public void  setUser(User user) {
        this.user = user;
    }
    public boolean isPublish() {
        return publish;
    }
    public void setPublish(boolean publish) {
        this.publish = publish;
    }
    public List<Result> getResult(){
        return result;
    }
    public void setResult(List<Result> result) {
        this.result = result;
    }
    public void addResult(Result result){
        this.result.add(result);
    }
}

