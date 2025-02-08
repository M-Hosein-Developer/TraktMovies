package ir.androidcoder.domain.base

sealed class Error : Exception() {

    // server
    object Internet : Error() // retrofit timeout

    data class ServerError(override val message: String?) : Error() // 4xx

    object Unknown : Error() // 5xx

    object TokenExpired : Error() // 401

    object NotFound : Error() // 404

    object NotLoggedIn : Error() // refresh token is empty

    object AccessDenied : Error() // 403

    object EmptyResult : Error() // 200 but without and data

    object ChatDisconnected : Error()

    // local

    object NoCache : Error()

    object CheckedOnce : Error()

    object NationalCodeNotMatch : Error()

    object CantChangePhoneNumber : Error()

    object LotteryPiggyExists : Error()

    // fields validation error

    object Empty : Error()

    object WrongFormat : Error()

    object CreditNotEnough : Error()

    object FinancialLevelError : Error()

    object NumberOnly : Error()

    // family

    object NoFamily : Error()


    object SpouseNotLoggedIn : Error()

    object SpouseRelationExists : Error()

    object SpouseRequestExists : Error()

    object SpouseHasFamily : Error()

    object AlreadyHasPasargadAccount : Error()



    // edit profile
    object NoFirstName : Error()

    object NoLastName : Error()

    object NoNationalCode : Error()

    object NationalCodeError : Error()

    object NoNationalCardSerial : Error()

    object NationalCardSerialWrong : Error()

    object NoVideo : Error()

    object NoBirthday : Error()

    // create piggy errors
    object NoChildId : Error()

    object NoTitle : Error()

    object NoAmount : Error()

    object NoEndDate : Error()

    object NoLottery : Error()

    // Create address errors
    object NoState : Error()

    object NoCity : Error()

    object NoAddress : Error()

    object WrongAddress : Error()

    object NoPostalCode : Error()

    object WrongPostalCode : Error()

    object NoPhone : Error()

    // School

    object NoSchool : Error()

    // Allowance

    object NoPeriodType : Error()

    // upload file error

    object NoFile: Error()

    class UploadFileError(
        val statusCode: Int?,
        val refNumber: String?
    ) : Error()

    object ReferrerCodeEmpty: Error()

    object ReferrerCodeWrong: Error()

    // pin code
    object NoPinCode: Error()

    object PinCodeWrong: Error()

    object NoRepeatPinCode: Error()

    object RepeatPinCodeNotMatch: Error()

    object NoOldPinCode: Error()

    object OldPinCodeWrong: Error()

    // One Click
    object SellOver: Error()

    object AlreadyBoughtOne: Error()

    object NotEnoughProduct: Error()
}