package org.baps.krl.dto

import java.time.LocalDate

data class FormResponse (
    val memberId: Int,
    val date: LocalDate,
    val answerIds: List<Int>
)