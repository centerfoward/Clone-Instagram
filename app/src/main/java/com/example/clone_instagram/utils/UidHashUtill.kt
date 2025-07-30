package com.example.clone_instagram.utils

import java.security.MessageDigest

object UidHashUtil {

    /**
     * UID를 SHA-256 해시 문자열로 변환
     * @param uid Firebase 인증 후 받은 UID
     * @return SHA-256으로 해싱된 UID 문자열 (Hex 형식)
     */
    fun hash(uid: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(uid.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}
