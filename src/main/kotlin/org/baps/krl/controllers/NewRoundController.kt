package org.baps.krl.controllers

import org.baps.krl.dto.NewTeamsDTO
import org.baps.krl.dto.NewMembersDTO
import org.baps.krl.dto.NewQuestionsDTO
import org.baps.krl.exceptions.DuplicateMemberInTeamException
import org.baps.krl.exceptions.MemberDoesNotExistException
import org.baps.krl.exceptions.RoundDoesNotExistException
import org.baps.krl.services.MembersService
import org.baps.krl.services.NewRoundsService
import org.baps.krl.services.QuestionsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class NewRoundConroller(
        @Autowired val membersService: MembersService,
        @Autowired val newRoundsService: NewRoundsService,
        @Autowired val questionsService: QuestionsService
){

    @CrossOrigin
    @PostMapping("/newRound/teams")
    fun newTeams(@RequestBody newTeams: NewTeamsDTO): ResponseEntity<String> {

        try{
            newRoundsService.addTeams(newTeams)
            return ResponseEntity("Teams Added", HttpStatus.OK)
        } catch (e: DuplicateMemberInTeamException) {
            return ResponseEntity("Duplicates " + e.message, HttpStatus.BAD_REQUEST)
        } catch (e: MemberDoesNotExistException) {
            return ResponseEntity("Member not found " + e.message, HttpStatus.BAD_REQUEST)
        }

    }

    @CrossOrigin
    @PostMapping("/newRound/newMembers")
    fun newMembers(@RequestBody NewMembersDTO: NewMembersDTO): ResponseEntity<String> {

        membersService.addMembers(NewMembersDTO)

        return ResponseEntity("Members Added", HttpStatus.OK)
    }

    @CrossOrigin
    @PostMapping("/newRound/newQuestion")
    fun newQuestions(@RequestBody newQuestionsDTO: NewQuestionsDTO): ResponseEntity<String> {

        try {
            questionsService.addQuestions(newQuestionsDTO)
        } catch(e: RoundDoesNotExistException){
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity("Questions Added", HttpStatus.OK)
    }
}