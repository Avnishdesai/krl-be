package org.baps.krl.controllers

import org.baps.krl.dto.FormResponse
import org.baps.krl.exceptions.NullAnswerProvidedException
import org.baps.krl.services.ResponsesService
import org.baps.krl.utils.LoggerProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class ResponsesController(
        @Autowired val responsesService: ResponsesService
) {

    val logger = LoggerFactory.getLogger(javaClass)

    @CrossOrigin()
    @GetMapping("/getApplicableSubmissionDates/{memberId}")
    fun getApplicableSubmissionDates(@PathVariable("memberId") memberId: Int): Set<LocalDate>{
        println("Get dates")
        return responsesService.getApplicableSubmissionDates(memberId)
    }

    @CrossOrigin()
    @PostMapping("/response")
    fun postResponse(@RequestBody formResponse: FormResponse): ResponseEntity<String>{
        logger.info("formResponse: {}", formResponse)
        try {
            responsesService.saveResponse(formResponse)
        } catch (e: NullAnswerProvidedException){
            logger.error(e.message)
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
        logger.info("Return Success")
        return ResponseEntity(HttpStatus.OK)

    }
}