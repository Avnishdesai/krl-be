package org.baps.krl.exceptions

class DuplicateMemberInTeamException(message:String): Exception(message)
class MemberDoesNotExistException(message:String): Exception(message)
class RoundDoesNotExistException(message:String): Exception(message)
class NullAnswerProvidedException(message:String): Exception(message)