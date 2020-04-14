package org.baps.krl.services

import org.baps.krl.db.Member
import org.baps.krl.db.MemberRepo
import org.baps.krl.dto.MemberDTO
import org.baps.krl.dto.NewMembersDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MembersService(
        @Autowired val memberRepo: MemberRepo
){
    fun getMembers(): Iterable<Member>{
        return memberRepo.findAll()
    }

    fun addMembers(NewMembersDTO: NewMembersDTO) {
        val newMembers = mutableListOf<Member>()
        NewMembersDTO.members.forEach { memberDTO: MemberDTO ->
            newMembers.add(Member(
                    id = null,
                    name = memberDTO.name
            ))
        }
        memberRepo.saveAll(newMembers)
    }
}