package com.geang0517.oopByTests.ch10;

import com.sun.tools.javac.Main;

public class ApplicationRunner {
    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PASSWORD = "sniper";
    private AuctionSniperDriver driver;


    public void startBiddingIn(final FakeAuctionServer auction) {
        Thread thread = new Thread("Test Application") {
            @Override
            public void run() {
                try {
                    Main.main(XMPP_HOSTNAME, SNIPER_ID, SNIPER_PASSWORD, auction.getItemId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true); //데몬스레드 = 보조스레드
        thread.start();
        driver = new AuctionSniperDriver(1000); //프레임과 컴포넌트를 찾기 위한 제한 시간 주기, 1초
        driver.showSniperStatus(STATUS_JOINING);
    }


    public void showSniperHasLostAuction() {
        driver.showSniperStatus(STATUS_LOST);
    }

    public void stop() {
        if(driver != null) {
            driver.dispose();
        }
    }
}
