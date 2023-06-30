package com.example.investment.network


import com.squareup.moshi.Json

data class Info(
    @Json(name = "0資料日期") val zero: String,
    @Json(name = "1金融機構代號") val one: String,
    @Json(name = "2金融機構名稱") val two: String,
    @Json(name = "3牌告利率項目") val three: String,
    @Json(name = "4牌告利率名稱") val four: String,
    @Json(name = "5存期起日") val five: String,
    @Json(name = "6存期迄日") val six: String,
    @Json(name = "7存期中文") val seven: String,
    @Json(name = "8額度代碼") val eight: String,
    @Json(name = "9額度代碼中文") val nine: String,
    @Json(name = "10生效日期") val ten: String,
    @Json(name = "11生效時間") val eleven: String,
    @Json(name = "12固定利率(2位整數/3位小數)") val twelve: String,
    @Json(name = "13機動利率(2位整數/3位小數)") val thirteen: String
)