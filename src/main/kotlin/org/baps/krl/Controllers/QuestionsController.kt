package org.baps.krl.Controllers

import org.baps.krl.Services.QuestionsService
import org.baps.krl.Services.RoundsService
import org.baps.krl.db.Question
import org.baps.krl.db.Round
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QuestionsController(
        @Autowired val questionsService: QuestionsService,
        @Autowired val roundsService: RoundsService
) {

    @GetMapping("/")
    fun getQuestions(): Iterable<Question>{
        return questionsService.getQuestions()
    }

    @GetMapping("/getCurrentQuestions")
    fun getCurrentQuestions(): Iterable<Question>{

        return questionsService.getQuestionsByRound(roundsService.getCurrentRound())
    }
}