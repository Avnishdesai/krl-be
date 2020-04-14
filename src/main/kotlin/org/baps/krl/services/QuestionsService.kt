package org.baps.krl.services

import org.baps.krl.db.Question
import org.baps.krl.db.QuestionRepo
import org.baps.krl.db.Round
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionsService(
        @Autowired val questionRepo: QuestionRepo
){
    fun getQuestions(): Iterable<Question>{
        return questionRepo.findAll()
    }

    fun getQuestionsByRound(round: Round): Iterable<Question>{
        return questionRepo.findAllByRound(round)
    }
}