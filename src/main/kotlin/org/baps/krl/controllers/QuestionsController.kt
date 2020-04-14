package org.baps.krl.controllers

import org.baps.krl.services.QuestionsService
import org.baps.krl.services.RoundsService
import org.baps.krl.db.Question
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QuestionsController(
        @Autowired val questionsService: QuestionsService,
        @Autowired val roundsService: RoundsService
) {

    @CrossOrigin()
    @GetMapping("/getQuestions")
    fun getQuestions(): Iterable<Question>{
        return questionsService.getQuestions()
    }

    @CrossOrigin()
    @GetMapping("/getCurrentQuestions")
    fun getCurrentQuestions(): Iterable<Question>{

        return questionsService.getQuestionsByRound(roundsService.getCurrentRound())
    }
}