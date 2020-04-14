package org.baps.krl.controllers

import org.baps.krl.dto.FormResponse
import org.baps.krl.services.ResponsesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class ResponsesController(
        @Autowired val responsesService: ResponsesService
) {

    @CrossOrigin()
    @GetMapping("/getApplicableSubmissionDates/{memberId}")
    fun getApplicableSubmissionDates(@PathVariable("memberId") memberId: Int): Set<LocalDate>{
        return responsesService.getApplicableSubmissionDates(memberId)
    }

    @CrossOrigin()
    @PostMapping("/response")
    fun postResponse(@RequestBody formResponse: FormResponse): ResponseEntity<String>{
        responsesService.saveResponse(formResponse)

        return ResponseEntity("Response Stored", HttpStatus.OK)

    }
}