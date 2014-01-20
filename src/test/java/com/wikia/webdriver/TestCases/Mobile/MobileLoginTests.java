package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileSpecialUserLogin;

/**
 * @author PMG
 *
 * Below test cases are executed against mobileregressiontesting wikiName with CHROMEMOBILE browser
 * 1. Verify that user is able to login successfully using standard login
 * 2. Verify that user is not logged in when he is using incorrect credentials
 * 3. Verify that user is able to login successfully using FB login
 */
public class MobileLoginTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"MobileLogin_001", "MobileLogin", "Mobile"})
	public void MobileLogin_001_successLoginDropDown() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		String url = driver.getCurrentUrl();
		mobile.loginDropDown(credentials.userName, credentials.password);
		mobile.verifyURLcontains(url);
	}

	@Test(groups={"MobileLogin_002", "MobileLogin", "Mobile"})
	public void MobileLogin_002_failedLoginWrongPassword() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		System.out.println(credentials.userName12);
		MobileSpecialUserLogin login = mobile.loginFailedDropDown(credentials.userName12, mobile.getTimeStamp());
		login.verifyWrongPasswordErrorMessage();
	}


	@Test(groups={"MobileLogin_003", "MobileLogin","Mobile"})
	public void MobileLogin_003_facebookLogin() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		mobile.openRandomPage();
		mobile.clickLoginFBButton();
		mobile.verifyFBLogin();
	}

}
