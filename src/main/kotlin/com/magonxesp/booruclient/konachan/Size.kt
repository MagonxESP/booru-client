package com.magonxesp.booruclient.konachan

data class Size(
	val compare: SizeCompare,
	val width: Int?,
	val height: Int?
) {
	private fun withCompare(size: Int) = when (compare) {
		SizeCompare.BIGGER_THAN -> "$size.."
		SizeCompare.EXACT -> "$size"
		SizeCompare.SMALLER -> "..$size"
	}

	fun widthSearch(): String? = width?.let { "width:${withCompare(it)}" }
	fun heightSearch(): String? = height?.let { "height:${withCompare(it)}" }

	companion object {
		// width
		const val WIDTH_2560 = 2560
		const val WIDTH_1920 = 1920
		const val WIDTH_1680 = 1680
		const val WIDTH_1600 = 1600
		const val WIDTH_1440 = 1440
		const val WIDTH_1400 = 1400
		const val WIDTH_1280 = 1280
		const val WIDTH_1152 = 1152
		const val WIDTH_1024 = 1024
		val WIDTH_NONE = null

		// height
		const val HEIGHT_1600 = 1600
		const val HEIGHT_1200 = 1200
		const val HEIGHT_1080 = 1080
		const val HEIGHT_1050 = 1050
		const val HEIGHT_1024 = 1024
		const val HEIGHT_960 = 960
		const val HEIGHT_900 = 900
		const val HEIGHT_864 = 864
		const val HEIGHT_800 = 800
		const val HEIGHT_768 = 768
		val HEIGHT_NONE = null
	}
}
