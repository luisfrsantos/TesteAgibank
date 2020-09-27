package com.example.luisfernandorodrigues.testeagiplan.helper

import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class HashGenerate {

    val ts: String by lazy {
        SimpleDateFormat(PATTERN, Locale.getDefault())
                .format(Date())
                .replace(".", "")
    }

    fun getHash(ts: String, privatekey: String, apikey: String): String {
        val x = ts + privatekey + apikey
        val d: java.security.MessageDigest? = java.security.MessageDigest.getInstance("MD5")
        d?.reset()
        d?.update(x.toByteArray())

        val mdbytes = d?.digest()
        return BigInteger(SIGNUM, mdbytes).toString(RADIX)
    }

    companion object {
        private const val PATTERN = "yyyy.MM.dd.HH.mm.ss"
        private const val RADIX = 16
        private const val SIGNUM = 1
    }
}