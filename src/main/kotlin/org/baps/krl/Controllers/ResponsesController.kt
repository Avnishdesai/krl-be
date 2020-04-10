package org.baps.krl.Controllers

import org.baps.krl.DTOs.FormResponse
import org.baps.krl.Services.ResponsesService
import org.springframework.beans.factory.annotation.Autowired
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
    fun postResponse(@RequestBody formResponse: FormResponse){
        responsesService.saveResponse(formResponse)


    }
}