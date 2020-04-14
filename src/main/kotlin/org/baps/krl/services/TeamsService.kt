package org.baps.krl.services

import org.baps.krl.db.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeamsService(
        @Autowired val teamRepo: TeamRepo
){
    fun getTeams(): Iterable<Team>{
        return teamRepo.findAll()
    }
}