package com.magonxesp.booruclient


class Tag(val value: String) {
	init {
		validate()
	}

	private fun validate() {
		if (!Regex("[a-zA-Z*]+(?:_[a-zA-Z*]+)*").matches(value)) {
			throw ClientException.InvalidTagFormat("The format of the tag is invalid, the valid format is for example: suzumiya_haruhi")
		}
	}
}
