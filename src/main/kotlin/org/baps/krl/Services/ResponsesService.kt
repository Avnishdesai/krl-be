package org.baps.krl.Services

import org.baps.krl.DTOs.FormResponse
import org.baps.krl.db.*
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

        return allDatesSet - submittedSet

    }

    fun saveResponse(formResponse: FormResponse) {
        formResponse.answerIds.forEach{answerId: Int ->
            val response = Response(
                    id = null,
                    dateCreated = Timestamp.valueOf(LocalDateTime.now()),
                    member = memberRepo.findById(formResponse.memberId).orElse(null),
                    applicableDate = Date.valueOf(formResponse.date),
                    answer = answerRepo.findById(answerId).orElse(null)
            )
            responseRepo.save(response)
        }
    }
}