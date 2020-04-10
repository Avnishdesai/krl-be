package org.baps.krl.DTOs

import java.time.LocalDate

data class FormResponse (
    val memberId: Int,
    val date: LocalDate,
    val answerIds: List<Int>
)