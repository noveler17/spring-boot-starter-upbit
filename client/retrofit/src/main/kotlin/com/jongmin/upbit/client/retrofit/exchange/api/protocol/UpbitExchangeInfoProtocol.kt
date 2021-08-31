package com.jongmin.upbit.client.retrofit.exchange.api.protocol

import com.fasterxml.jackson.annotation.JsonProperty
import com.jongmin.upbit.exchange.info.UpbitWalletStatus

data class UpbitWalletStatusResponse(
    /**
     * 설명: 화폐를 의미하는 영문 대문자 코드
     * 타입: String
     */
    @JsonProperty("currency")
    val currency: String,

    /**
     * 설명: 입출금 상태
     * 타입: String
     */
    @JsonProperty("wallet_state")
    val walletState: String,

    /**
     * 설명: 블록 상태
     * 타입: String
     */
    @JsonProperty("block_state")
    val blockState: String,

    /**
     * 설명: 블록 높이
     * 타입: Integer
     */
    @JsonProperty("block_height")
    val blockHeight: String,

    /**
     * 설명: 블록 갱신 시각
     * 타입: DateString
     */
    @JsonProperty("block_updated_at")
    val blockUpdatedAt: String
)

fun UpbitWalletStatusResponse.toDomain() = UpbitWalletStatus(
    currency = currency,
    walletState = walletState,
    blockState = blockState,
    blockHeight = blockHeight,
    blockUpdatedAt = blockUpdatedAt
)
