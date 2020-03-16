package org.baps.krl.db

import java.sql.Date
import java.sql.Timestamp
import javax.persistence.*

@Entity
class Member(
        @Id @GeneratedValue var id: Int,
        var name: String

)

@Entity
class Round(
        @Id @GeneratedValue var id: Int,
        var seq: Int,
        var startDate: Date,
        var endDate: Date
)


@Entity
class Team(
        @Id @GeneratedValue var id: Int,
        var name: String,
        @ManyToOne var round: Round
)

@Entity
class TeamMember(
        @Id @GeneratedValue var id: Int,
        @ManyToOne var round: Round,
        @OneToOne var member: Member,
        @ManyToOne var team: Team
)

@Entity
class Question(
        @Id @GeneratedValue var id: Int,
        var description: String,
        @ManyToOne var round: Round,
        var points: Int
)

@Entity
class Response(
        @Id @GeneratedValue var id: Int,
        var dateCreated: Timestamp,
        var applicableDate: Date,
        @ManyToOne var question: Question,
        var value: String,
        @OneToOne var member: Member
)