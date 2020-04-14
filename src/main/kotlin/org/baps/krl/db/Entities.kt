package org.baps.krl.db

import java.sql.Date
import java.sql.Timestamp
import javax.persistence.*

@Entity
class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        var name: String

)

@Entity
class Round(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        var startDate: Date,

        var endDate: Date?,

        var currentRound: Boolean
)


@Entity
class Team(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        var name: String,

        @ManyToOne(cascade = [CascadeType.ALL])
        var round: Round,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "id")
        var members: List<Member>
)

@Entity
class Question(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        var description: String,

        @ManyToOne
        var round: Round,

        @OneToMany
        @JoinColumn(name = "question_id")
        var answers: List<Answer>
)

@Entity
class Answer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        var text: String,

        var points: Int
)

@Entity
class Response(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,

        var dateCreated: Timestamp?,

        var applicableDate: Date,

        @OneToOne
        var answer: Answer,

        @OneToOne
        var member: Member
)