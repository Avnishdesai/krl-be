package org.baps.krl.dto

data class  NewTeamsDTO (
        val teams: List<TeamDTO>
)

data class TeamDTO (
        val name: String,
        val members: List<MemberDTO>
)

data class MemberDTO (
        val name: String
)

data class NewMembersDTO (
        val members: List<MemberDTO>
)