package org.baps.krl.services

import org.baps.krl.db.*
import org.baps.krl.dto.AnswerDTO
import org.baps.krl.dto.NewQuestionsDTO
import org.baps.krl.dto.QuestionDTO
import org.baps.krl.exceptions.RoundDoesNotExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class QuestionsService(
        @Autowired val questionRepo: QuestionRepo,
        @Autowired val roundRepo: RoundRepo
){
    fun getQuestions(): Iterable<Question>{
        return questionRepo.findAll()
    }

    fun getQuestionsByRound(round: Round): Iterable<Question>{
        return questionRepo.findAllByRound(round)
    }

    fun addQuestions(newQuestionsDTO: NewQuestionsDTO) {
        val round = roundRepo.findById(newQuestionsDTO.roundId).orElse(null)
        if (round == null){
            throw RoundDoesNotExistException("Rounds does not exist: {}" + newQuestionsDTO.roundId)
        }
        val newQuestions = mutableListOf<Question>()

        newQuestionsDTO.questions.forEach{q: QuestionDTO ->
            val newAnswers = mutableListOf<Answer>()
            q.answers.forEach{a: AnswerDTO ->
                newAnswers.add(
                        Answer(
                                id = null,
                                text = a.text,
                                points = a.points
                        )
                )
            }
            newQuestions.add(
                    Question(
                            id = null,
                            description = q.description,
                            round = round,
                            answers = newAnswers
                    )
            )
        }
        questionRepo.saveAll(newQuestions)
    }
}