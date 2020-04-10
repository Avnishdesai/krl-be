package org.baps.krl.Controllers

import org.baps.krl.Services.MembersService
import org.baps.krl.db.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MembersController(
        @Autowired val membersService: MembersService
){

    @CrossOrigin()
    @GetMapping("/getMembers")
    fun getMembers(): Iterable<Member>{
        return membersService.getMembers()
    }
}