package org.baps.krl.services

import org.baps.krl.db.*
import org.baps.krl.dto.MemberDTO
import org.baps.krl.dto.NewTeamsDTO
import org.baps.krl.dto.TeamDTO
import org.baps.krl.exceptions.DuplicateMemberInTeamException
import org.baps.krl.exceptions.MemberDoesNotExistException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.sql.Date
import java.time.LocalDate

@Service
class NewRoundsService(
        @Autowired val roundsRepo: RoundRepo,
        @Autowired val memberRepo: MemberRepo,
        @Autowired val teamRepo: TeamRepo
){
    fun addTeams(newTeamsDTO: NewTeamsDTO){
//        closeCurrentRound()
        val round = newRound()
        roundsRepo.save(round)

        val duplicateMembers = mutableListOf<String>()
        var names = mutableListOf<String>()
        val newTeams = mutableListOf<Team>()
        newTeamsDTO.teams.forEach{team: TeamDTO ->
            val membersList = mutableListOf<Member>()
            for (member: MemberDTO in team.members){

                if (names.contains(member.name)){
                    duplicateMembers.add(member.name)
                } else {
                    try {
                        val m = memberRepo.findByName(member.name)
                        names.add(member.name)
                        membersList.add(m)
                    } catch (e: EmptyResultDataAccessException){
                        throw MemberDoesNotExistException("Member does not exist: " + member.name)
                    }

                }
            }


            newTeams.add(Team(
                    id = null,
                    round = round,
                    name = team.name,
                    members = membersList
            ))
        }

        if (!duplicateMembers.isEmpty()){
            throw DuplicateMemberInTeamException(duplicateMembers.toString())
        }

        teamRepo.saveAll(newTeams)
    }

    private fun newRound(): Round {
        return Round(
                id = null,
                startDate = Date.valueOf(LocalDate.now()),
                endDate = null,
                currentRound = false
        )
    }

    private fun closeCurrentRound() {
        var currRound = roundsRepo.findByCurrentRound(true)
        currRound.endDate = Date.valueOf(LocalDate.now())
        roundsRepo.save(currRound)
    }
}