package org.baps.krl.db

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MemberRepo: CrudRepository<Member, Int> {
    fun findByName(name: String): Member
}

interface RoundRepo: CrudRepository<Round, Int> {
    fun findByCurrentRound(isCurrentRound: Boolean): Round
}

interface TeamRepo: CrudRepository<Team, Int>

interface QuestionRepo: CrudRepository<Question, Int> {
    fun findAllByRound(round: Round): List<Question>
}

interface ResponseRepo: CrudRepository<Response, Int> {

    fun findAllByMember(member: Member): List<Response>
}

interface AnswerRepo: CrudRepository<Answer, Int>
