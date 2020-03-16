package org.baps.krl.Services

import org.baps.krl.db.Question
import org.baps.krl.db.QuestionRepo
import org.baps.krl.db.Round
import org.baps.krl.db.RoundRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoundsService(
        @Autowired val roundRepo: RoundRepo
){
    fun getRounds(): Iterable<Round>{
        return roundRepo.findAll()
    }

    fun getCurrentRound(): Round{
        return roundRepo.findCurrentRound()
    }
}