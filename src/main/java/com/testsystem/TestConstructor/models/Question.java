package com.testsystem.TestConstructor.models;
//https://github.com/paveltinnik/jetbrains-web-quiz-engine/blob/master/src/main/java/com/paveltinnik/webquizengine/engine/entities/AnswerFromUser.java
import javax.persistence.*;
import java.util.List;
//это класс, представляющий один вопрос викторины; каждый вопрос будет иметь id
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answer;

    @ManyToOne
    private Test test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
