package org.baps.krl.controllers

import org.baps.krl.services.MembersService
import org.baps.krl.db.Member
import org.baps.krl.db.MemberRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MembersController(
        @Autowired val membersService: MembersService,
        @Autowired val memberRepo: MemberRepo
){

    @CrossOrigin
    @GetMapping("/getMembers")
    fun getMembers(): Iterable<Member>{
        return membersService.getMembers()
    }

    @CrossOrigin
    @GetMapping("/getMember")
    fun getMember(): Member{
        return memberRepo.findByName("Avnish Desai")
    }
}