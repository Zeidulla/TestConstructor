package com.testsystem.TestConstructor.service;

import com.testsystem.TestConstructor.models.*;
import com.testsystem.TestConstructor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    ResultRepository resultRepository;

    public Long addNewTest(String username, String name, String description) {
        User u = userRepository.findByUsername(username);
        Test test = new Test(u, name, description);
        u.addTest(test);
        testRepository.save(test);
        userRepository.save(u);
        return test.getId();
    }

    public void addQuestion(Long idTest, String question, List<String> answers, Long trueAns) {
        Test test = testRepository.findById(idTest).get();
        Question quest = new Question();
        quest.setQuestion(question);
        quest.setTest(test);
        questionRepository.save(quest);
        List<Answer> answerList = new ArrayList<Answer>();
        for (int i = 0; i < answers.size(); i++) {
            if (i == trueAns) {
                Answer tempAns = new Answer(quest, answers.get(i), true);
                answerList.add(tempAns);
                answerRepository.save(tempAns);
            } else {
                Answer tempAns = new Answer(quest, answers.get(i), false);
                answerList.add(tempAns);
                answerRepository.save(tempAns);
            }
        }
        quest.setAnswer(answerList);
        test.addQuest(quest);
        questionRepository.save(quest);
        testRepository.save(test);

    }

    public void deleteQuest(Long id) {
        questionRepository.deleteForeignKey(id);
        if (questionRepository.findById(id) != null) questionRepository.deleteById(id);
    }

    public void deleteTest(Long id) {
        if (testRepository.findById(id) != null) {
            Test test = testRepository.findById(id).get();
            User user = test.getUser();
            for(int i=0; i<test.getResult().size();i++)
            {
                System.out.println(test.getResult().size());
                userRepository.deleteForeignKeyUsRes2(test.getResult().get(i).getId());
            }

            testRepository.deleteForeignKeyTestRes(id);
            // testRepository.deleteForeignKeyRes(id);
            testRepository.deleteForeignKey(id);
            testRepository.deleteById(id);
        }
    }

    public int getResult(Long testId, List<Long> answers) {
        int result=0;
        Test test = testRepository.findById(testId).get();
        for (int i = 0; i < answers.size(); i++) {
            if (test.getQuestion().get(i).getAnswer().get(answers.get(i).intValue()).isFlag() == true) result++;
        }
        return result;
    }
    public void setPublic(Long id){
        Test test = testRepository.findById(id).get();
        test.setPublish(true);
        testRepository.save(test);
    }
    public boolean isOwner(Long testId, String username){
        Test test = testRepository.findById(testId).get();
        if (test.getUser().getUsername().equals(username)) return true;
        else return false;
    }
    public boolean isOwner(String username, Long questId){
        Test test = questionRepository.findById(questId).get().getTest();
        if (test.getUser().getUsername().equals(username)) return true;
        else return false;
    }
    public void newResult(String username, Long testId, Long trueAns){
        User user = userRepository.findByUsername(username);
        Test test = testRepository.findById(testId).get();
        Result result = new Result(trueAns,user,test);
        resultRepository.save(result);
        user.addResult(result);
        test.addResult(result);
        userRepository.save(user);
        testRepository.save(test);

    }
}
