package omarbradley.com.util

inline fun <T, R> withNullable(receiver: T?, block: T.() -> R): R? {
    return receiver?.block()
}
