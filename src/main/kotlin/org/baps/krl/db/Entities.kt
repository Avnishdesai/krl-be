package org.baps.krl.db

import java.sql.Date
import java.sql.Timestamp
import javax.persistence.*

@Entity
class Member(
        @Id @GeneratedValue val id: Int,
        val name: String

)

@Entity
class Round(
        @Id @GeneratedValue val id: Int,
        val seq: Int,
        val startDate: Date,
        val endDate: Date
)


@Entity
class Team(
        @Id @GeneratedValue val id: Int,
        val name: String,
        @ManyToOne val round: Round
)

@Entity
class TeamMember(
        @Id @GeneratedValue val id: Int,
        @ManyToOne val round: Round,
        @OneToOne val member: Member,
        @ManyToOne val team: Team
)

@Entity
class Question(
        @Id
        @GeneratedValue
        val id: Int,

        val description: String,

        @ManyToOne
        val round: Round,

        @OneToMany
        @JoinColumn(name="question_id")
        val answers: List<Answer>
)

@Entity
class Answer(
        @Id
        @GeneratedValue
        val id: Int,

        val text: String,

        val points: Int
)

@Entity
class Response(
        @Id
        @GeneratedValue
        val id: Int,

        val dateCreated: Timestamp,

        val applicableDate: Date,

        @ManyToOne
        val question: Question,

        val value: String,

        @OneToOne
        val member: Member
)