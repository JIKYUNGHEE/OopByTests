package com.geang0517.oopByTests.ch10;

import org.junit.After;
import org.junit.jupiter.api.Test;

//한 번에 한 도메인
public class AuctionSniperEndToEndTest {
    private final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
    private final ApplicationRunner application = new ApplicationRunner();

    @Test
    public void sniperJoinsAuctionUntilAuctionCloses() throws Exception {
        auction.startSellingItem();                 //1) 경매에서 품목을 판매
        application.startBiddingIn(auction);        //2) 경매 스나이퍼가 해당 경매에서 입찰을 시작
        auction.hadReceivedJoinRequestFromSniper(); //3) 경매에서는 경매 스나이퍼로부터 Join 요청을 받을 것이다.
        auction.announceClosed();                   //4) 경매가 Close 됐다고 선언되면
        application.showSniperHasLostAuction();     //5) 경매 스나이퍼는 경매에서 낙찰에 실패했음을 보여줄 것이다.
    }

    @After
    public void stopAction() {

    }

    @After
    public void stopApplication() {

    }
}
