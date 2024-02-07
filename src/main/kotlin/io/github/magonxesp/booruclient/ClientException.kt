package io.github.magonxesp.booruclient

sealed class ClientException(override val message: String? = null) : Exception(message) {
	class InvalidTagFormat(override val message: String? = null) : ClientException(message)
	class UnknownError(override val message: String? = null) : ClientException(message)
	class RequestFailed(override val message: String? = null) : ClientException(message)
}
