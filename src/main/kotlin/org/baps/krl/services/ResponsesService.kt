package org.baps.krl.services

import org.baps.krl.dto.FormResponse
import org.baps.krl.db.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ResponsesService(
        @Autowired val responseRepo: ResponseRepo,
        @Autowired val memberRepo: MemberRepo,
        @Autowired val roundsService: RoundsService,
        @Autowired val answerRepo: AnswerRepo
){
    val logger = LoggerFactory.getLogger(javaClass)

    fun getApplicableSubmissionDates(memberId: Int): Set<LocalDate> {
        val m: Member = memberRepo.findById(memberId).orElse(null)
        val submittedDates = responseRepo.findAllByMember(m)
        val submittedSet = hashSetOf<LocalDate>()
        submittedDates.forEach{
            submittedSet.add(it.applicableDate.toLocalDate())
        }

        val startDate = roundsService.getCurrentRound().startDate.toLocalDate()
        val today = LocalDate.now()

        val allDatesSet = hashSetOf<LocalDate>()

        var d = today
        while (d > startDate){
            allDatesSet.add(d)
            d = d.minusDays(1)
        }

        val retSet = allDatesSet - submittedSet
        println("Num returned dates: " + retSet.size)
        return retSet

    }

    fun saveResponse(formResponse: FormResponse) {
        try {
            formResponse.answerIds.forEach { answerId: Int ->
                val answer = memberRepo.findById(formResponse.memberId).orElse(null)
                val response = Response(
                        id = null,
                        dateCreated = Timestamp.valueOf(LocalDateTime.now()),
                        member = ,
                        applicableDate = Date.valueOf(formResponse.date),
                        answer = answerRepo.findById(answerId).orElse(null)
                )
                responseRepo.save(response)
                logger.info("Response to save: {}", response)
            }
        } catch ()
    }
}