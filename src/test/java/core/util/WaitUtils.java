package core.util;

import core.driver.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This will contain all wait related utility methods.
 *
 * @author prat3ik
 */
public class WaitUtils {

	private Boolean isAngularDefined(final WebDriver driver) {
		boolean isAngularDefined = false;
		isAngularDefined = (Boolean) ((JavascriptExecutor) driver)
				.executeScript("return (typeof(angular)!=='undefined')");
		return isAngularDefined;
	}

	public final int explicitWaitDefault;

	public WaitUtils() {
		this.explicitWaitDefault = PropertyUtils.getIntegerProperty("explicitWait", 50);
	}

	protected WebDriver getDriver() {
		return WebDriverManager.getThreadLocalDriver();
	}

	/**
	 * This will return count of in-progress HTTP calls
	 *
	 * @param driver - WebDriver object
	 * @return return count of running HTTP requests
	 */
	private int getHttpRequestsCount(final WebDriver driver) {
		int runningReqs = 0;
		String pendingReq = "";
		try {
			final String script = "return (typeof(angular)!=='undefined' ? (angular.element('.ng-scope').injector() ? angular.element('.ng-scope').injector().get('$http').pendingRequests.length : 999) : 0) + ''";
			pendingReq = (String) ((JavascriptExecutor) driver).executeScript(script);
			runningReqs = Integer.parseInt(pendingReq);

		} catch (final Exception e) {
			// this.logger.error("Exception executing script or incorrect
			// response", e);
		}

		return runningReqs;
	}

	/**
	 * This will check if there is any http call running through angular If any
	 * http call is in progress, it will return true. If there is no http call
	 * running, it will return false
	 *
	 * @param driver - WebDriver object
	 * @return true if any request is processing, false otherwise
	 */
	private boolean isDataLoading(final WebDriver driver) {
		final int runningReqs = this.getHttpRequestsCount(driver);
		final boolean isDataLoading = runningReqs != 0;
		return isDataLoading;
	}

	/**
	 * This method is for static wait
	 *
	 * @param millis
	 */
	public void staticWait(final long millis) {
		try {
			TimeUnit.MILLISECONDS.sleep(millis);
		} catch (final InterruptedException e) {
		}
	}

	/**
	 * To wait for given element (By) to be present
	 *
	 * @param locator
	 */
	public void waitForElementToBePresent(final By locator) {
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForPageToLoad() {
		long s = System.currentTimeMillis();
		final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(final WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		final Wait<WebDriver> wait = new WebDriverWait(WebDriverManager.getThreadLocalDriver(), explicitWaitDefault);
		wait.until(expectation);
		// this.logger.info("Page load completed. " +
		// (System.currentTimeMillis() - s));
	}

	/**
	 * To wait for element to be visible
	 *
	 * @param element
	 */
	public void waitForElementToBeVisible(final WebElement element) {
		long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementsToBeInvisible(final List<WebElement> elements) {
		final long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	public void waitForElementToBeNotPresent(final By element) {
		long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(element)));
	}

	public void waitTillAngularToBePresent() {
		final long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), explicitWaitDefault).until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(final WebDriver d) {
				return WaitUtils.this.isAngularDefined(d);
			}
		});
	}

	public void waitTillDataIsFetched(String fieldName) {
		waitTillDataIsFetched(null, fieldName);
	}

	public void waitTillDataIsFetched() {
		waitTillDataIsFetched(null, "");
	}

	/**
	 * This method waits until all http calls finish processing. This would be
	 * helpful in case of data loading API calls
	 *
	 * @param fieldName TODO
	 */
	public void waitTillDataIsFetched(Integer explicitWait, String fieldName) {
		if (explicitWait == null) {
			explicitWait = this.explicitWaitDefault;
		}
		try {
			//this.waitTillAngularToBePresent();
		} catch (Exception e) {
		}
		final long s = System.currentTimeMillis();
		final int renderTime = PropertyUtils.getIntegerProperty("renderTime", 300);
		this.staticWait(renderTime);
		this.waitTillJQueryRequestCompletes(explicitWait);
		this.staticWait(renderTime);

	}

	private void waitTillAngularRequestCompletes(Integer explicitWait) {
		try {
			new WebDriverWait(this.getDriver(), explicitWait).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(final WebDriver d) {
					return !WaitUtils.this.isDataLoading(d);
				}
			});
		} catch (Exception e) {
			// logger.error(e);
			String script = "var list =angular.element('.ng-scope').injector().get('$http').pendingRequests;"
					+ "var urls = [];" + "list.forEach(function(obj){" + "urls.push(obj.url);" + "});" + "return urls;";
			String pendingReq = "";
			try {
				pendingReq = ((JavascriptExecutor) getDriver()).executeScript(script).toString();
			} catch (Exception e1) {
				// logger.error(e1);
			}
			throw new RuntimeException("Timed out [" + explicitWait
					+ " sec] waiting for HTTP requests to complete. Pending Requests are : " + pendingReq, e);
		}
	}

	public void waitTillAngularFinishProcess() {
		waitTillAngularFinishProcess(this.explicitWaitDefault);
	}

	public void waitTillAngularFinishProcess(Integer explicitWait) {
		new WebDriverWait(this.getDriver(), explicitWait).until(angularHasFinishedProcessing());
	}

	private ExpectedCondition<Boolean> angularHasFinishedProcessing() {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				StringBuilder hasAngularFinishedScript = new StringBuilder(
						"var callback = arguments[arguments.length - 1];")
						.append("var el = document.querySelector('html');\n").append("if (!window.angular) {\n")
						.append("    callback('true')\n").append("}\n")
						.append("if (angular.getTestability) {\n")
						.append("    angular.getTestability(el).whenStable(function(){callback('true')});\n")
						.append("} else {\n").append("    if (!angular.element(el).injector()) {\n")
						.append("        callback('false')\n").append("    }\n")
						.append("    var browser = angular.element(el).injector().get('$browser');\n")
						.append("    browser.notifyWhenNoOutstandingRequests(function(){callback('true')});\n")
						.append("}");
				String isProcessingFinished = "false";
				try {
					JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
					isProcessingFinished = javascriptExecutor.executeAsyncScript(hasAngularFinishedScript.toString())
							.toString();

				} catch (Exception e) {
				}
				return Boolean.valueOf(isProcessingFinished);
			}
		};
	}


	private void waitTillJQueryRequestCompletes(Integer explicitWait) {
		final long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), explicitWait).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(final WebDriver d) {
				return !WaitUtils.this.isJQueryRequestInProgress(d);
			}
		});
	}

	private Boolean isJQueryRequestInProgress(final WebDriver driver) {
		boolean isAngularDefined = false;
		isAngularDefined = (Boolean) ((JavascriptExecutor) driver)
				.executeScript("return (typeof(jQuery)!=='undefined' ? jQuery.active!==0 : false)");
		return isAngularDefined;
	}

	private void ajaxComplete() {
		((JavascriptExecutor) getDriver()).executeScript("var callback = arguments[arguments.length - 1];"
				+ "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
				+ "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
				+ "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
	}

	private void waitForJQueryLoad() {
		try {
			ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) this.getDriver())
					.executeScript("return jQuery.active") == 0);

			boolean jqueryReady = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active==0");

			if (!jqueryReady) {
				new WebDriverWait(this.getDriver(), this.explicitWaitDefault).until(jQueryLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	private void waitForAngularLoad() {
		String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
		angularLoads(angularReadyScript);
	}

	private void waitUntilJSReady() {
		try {
			ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) this.getDriver())
					.executeScript("return document.readyState").toString().equals("complete");

			boolean jsReady = ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString().equals("complete");

			if (!jsReady) {
				new WebDriverWait(this.getDriver(), this.explicitWaitDefault).until(jsLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	public void waitUntilJQueryReady() {
		Boolean jQueryDefined = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return typeof jQuery != 'undefined'");
		if (jQueryDefined) {
			staticWait(20);

			waitForJQueryLoad();

			staticWait(20);
		}
	}

	public void waitUntilAngularReady() {
		try {
			Boolean angularUnDefined = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return window.angular === undefined");
			if (!angularUnDefined) {
				Boolean angularInjectorUnDefined = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return angular.element(document).injector() === undefined");
				if (!angularInjectorUnDefined) {
					staticWait(20);

					waitForAngularLoad();

					staticWait(20);
				}
			}
		} catch (WebDriverException ignored) {
		}
	}

	public void waitUntilAngular5Ready() {
		try {
			Object angular5Check = ((JavascriptExecutor) getDriver()).executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
			if (angular5Check != null) {
				Boolean angularPageLoaded = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
				if (!angularPageLoaded) {
					staticWait(20);
					waitForAngular5Load();
					staticWait(20);
				}
			}
		} catch (WebDriverException ignored) {
		}
	}

	private void waitForAngular5Load() {
		String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
		angularLoads(angularReadyScript);
	}

	private void angularLoads(String angularReadyScript) {
		try {
			ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
					.executeScript(angularReadyScript).toString());

			boolean angularReady = Boolean.valueOf(((JavascriptExecutor) getDriver()).executeScript(angularReadyScript).toString());

			if (!angularReady) {
				new WebDriverWait(this.getDriver(), this.explicitWaitDefault).until(angularLoad);
			}
		} catch (WebDriverException ignored) {
		}
	}

	public void waitAllRequest() {
		waitUntilJSReady();
		ajaxComplete();
		waitUntilJQueryReady();
		waitUntilAngularReady();
		waitUntilAngular5Ready();
	}
}