package ir.androidcoder.data.model.adapter

data class ResponseError(val message : MutableList<Error>?){


    fun getErrorMessage(): String {
        var addErrorMessage = ""
        message?.forEach{ error ->
            addErrorMessage += error.message
            if (message.last() != error)
                addErrorMessage += ", "
        }

        return addErrorMessage
    }

    fun getErrorCode() : Int? {
        message?.forEach{ error ->
            return error.code
        }
        return null
    }

}


data class Error(
    val code: Int,
    val message: String
)