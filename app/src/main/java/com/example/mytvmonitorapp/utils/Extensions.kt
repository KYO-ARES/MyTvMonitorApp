// 扩展函数
fun String.isValidIpAddress(): Boolean {
    val pattern = Regex("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    return pattern.matches(this)
}

fun String.isValidPort(): Boolean {
    return toIntOrNull()?.let { it in 1..65535 } ?: false
}