package com.jongmin.upbit.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.math.BigInteger
import java.security.InvalidParameterException
import java.security.MessageDigest

class TokenServiceImpl(
    private val properties: TokenProperties,
    private val nonceGenerator: () -> String
) : TokenService {
    override fun createToken(parameter: Map<String, Any>): String {
        return Jwts.builder().apply {
            claim("access_key", properties.accessKey)
            claim("nonce", nonceGenerator())
            if (parameter.isNotEmpty()) {
                claim("query_hash", parameter.toQueryString().hashing())
                claim("query_hash_alg", "SHA512")
            }
            signWith(Keys.hmacShaKeyFor(properties.secretKey.toByteArray()))
        }.compact()
    }
}

fun Map<String, Any>.toQueryString(): String {
    val queryElements = mutableListOf<String>()
    this.forEach { entry ->
        when (entry.value) {
            is String -> queryElements.add("${entry.key}=${entry.value}")
            is Array<*> -> {
                (entry.value as Array<*>).forEach {
                    queryElements.add("${entry.key}[]=${it.toString()}")
                }
            }
            else -> throw InvalidParameterException("${entry.value.javaClass} is not supported type.")
        }
    }
    return queryElements.joinToString("&")
}

fun String.hashing(): String {
    return MessageDigest.getInstance("SHA-512").let {
        it.update(this.toByteArray())
        String.format("%0128x", BigInteger(1, it.digest()))
    }
}
