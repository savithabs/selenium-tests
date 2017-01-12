package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;
import sun.jvm.hotspot.utilities.AssertionFailure;

public class TestAdsFandomBtfBlock extends AdsFandomTestTemplate {

  private static final String ASSERT_MESSAGE = "Expected BTF slot to be blocked.";

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomBtfBlockPage",
      groups = {"AdsFandomBtfBlockDesktop"}
  )
  public void adsFandomBtfBlockDesktop(String article) {
    AdsFandomObject fandomPage = loadPage(article);

    fandomPage.triggerOnScrollSlots();

    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD, AdsFandomContent.GPT_TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD, AdsFandomContent.GPT_TOP_BOXAD_DESKTOP);

    Assertion.assertTrue(areBtfSlotsHidden(fandomPage), "BTF ads are displayed");
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomBtfBlockPage",
      groups = {"AdsFandomBtfBlockMobile"}
  )
  public void adsFandomBtfBlockMobile(String article) {
    AdsFandomObject fandomPage = loadPage(article);

    fandomPage.triggerOnScrollSlots();

    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD, AdsFandomContent.GPT_TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD, AdsFandomContent.GPT_TOP_BOXAD_MOBILE);

    Assertion.assertTrue(areBtfSlotsHidden(fandomPage), "BTF ads are displayed");
  }

  private boolean areBtfSlotsHidden(AdsFandomObject fandomPage) {
    try {
      Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_BOXAD), ASSERT_MESSAGE);
      Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.INCONTENT_BOXAD), ASSERT_MESSAGE);
      Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_LEADERBOARD), ASSERT_MESSAGE);
    }catch (AssertionFailure ae){
      PageObjectLogging.log("Btf ads are displayed", ae, true);
      return false;
    }
    return true;
  }
}
