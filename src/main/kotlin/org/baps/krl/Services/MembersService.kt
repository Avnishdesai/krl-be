package org.baps.krl.Services

import org.baps.krl.db.Member
import org.baps.krl.db.MemberRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MembersService(
        @Autowired val memberRepo: MemberRepo
){
    fun getMembers(): Iterable<Member>{
        return memberRepo.findAll()
    }
}