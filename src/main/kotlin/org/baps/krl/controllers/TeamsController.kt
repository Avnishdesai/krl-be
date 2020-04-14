package org.baps.krl.controllers

import org.baps.krl.services.TeamsService
import org.baps.krl.db.Team
import org.baps.krl.db.TeamRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TeamsController(
        @Autowired val teamsService: TeamsService,
        @Autowired val teamsRepo: TeamRepo
){
    @CrossOrigin()
    @GetMapping("/getTeams")
    fun getGetAllTeams(): Iterable<Team>{
        return teamsRepo.findAll()
    }
}