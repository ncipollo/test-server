package nickcipollo.com.test

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val code: String? = null,
    val message: String = "",
    val `object`: String? = null,
    val property: String? = null
)
