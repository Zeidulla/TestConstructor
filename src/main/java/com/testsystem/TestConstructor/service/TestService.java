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
    UserService userService;/* нужно добавить */

    @Autowired
    EmailService emailService;

    @Autowired
    ResultRepository resultRepository;/*  сделаю пометку */

    public Long addNewTest(String username, String name, String description) {
        User u = userRepository.findByUsername(username);
        Test test = new Test(u, name, description);
        u.addTest(test);
        testRepository.save(test);
        userRepository.save(u);
        return test.getId();
    }

    public void addQuestion(Long idTest, String question, List<String> answers, Long trueAns) {/*  сделаю пометку */
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
        if (questionRepository.findById(id) != null) questionRepository.deleteById(id);/*  сделаю пометку */
    }
    /* delete и getresult ...public owner..newresult  в разработке*/

}
