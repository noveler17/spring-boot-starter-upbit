package com.jongmin.upbit.exchange.deposit

interface UpbitDepositsService {

    /**
     * 입금 리스트 조회
     *
     * @param currency Currency 코드
     * @param state 입금 상태
     * @param uuids 입금 UUID의 목록
     * @param txids 입금 TXID의 목록
     * @param limit 페이지당 개수
     * @param page 페이지 번호
     * @param orderBy 정렬 방식
     * @return 입금 리스트
     */
    fun getDeposits(
        currency: String? = null,
        state: String? = null,
        uuids: List<String> = emptyList(),
        txids: List<String> = emptyList(),
        limit: Int? = null,
        page: Int? = null,
        orderBy: String? = null
    ): List<UpbitDeposit>

    /**
     * 개별 입금 조회
     *
     * @param uuid 개별 입금의 UUID
     * @param txid 개별 입금의 TXID
     * @param currency Currency 코드
     * @return 개별 입금 정보
     */
    fun getDeposit(uuid: String, txid: String? = null, currency: String? = null): UpbitDeposit

    /**
     * 입금 주소 생성 요청
     * 입금 주소 생성을 요청한다.
     *
     * @param currency Currency 코드
     * @return 입금 주소 생성 요청 결과 (주소 발급 이전) 또는 생성 결과 (주소 발급 완료)
     */
    fun createDepositCoinAddress(currency: String): UpbitCreateDepositCoinAddress

    /**
     * 전체 입금 주소 조회
     * 내가 보유한 자산 리스트를 보여줍니다.
     *
     * @return 보유한 입금 주소 리스트
     */
    fun getDepositsCoinAddresses(): List<UpbitDepositCoinAddress>

    /**
     * 개별 입금 주소 조회
     *
     * @param currency Currency 코드
     * @return 보유한 입금 주소
     */
    fun getDepositsCoinAddress(currency: String): UpbitDepositCoinAddress

    /**
     * 원화 입금하기
     * 원화 입금을 요청한다.
     *
     * @param amount 입금액
     * @return 입금 결과
     */
    fun depositKrw(amount: String): UpbitDepositKrw
}
