package org.baps.krl.db

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MemberRepo: CrudRepository<Member, Int> {}

interface RoundRepo: CrudRepository<Round, Int> {
    @Query("SELECT r FROM Round r WHERE r.endDate is null")
    fun findCurrentRound(): Round
}

interface TeamRepo: CrudRepository<Team, Int> {}

interface QuestionRepo: CrudRepository<Question, Int> {
    fun findAllByRound(round: Round): List<Question>
}

interface ResponseRepo: CrudRepository<Response, Int> {}

interface TeamMemberRepo: CrudRepository<TeamMember, Int> {}