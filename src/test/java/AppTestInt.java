import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTestInt extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootPageContains_EnterText() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Enter text");
  }

  @Test
  public void displayPuzzlePageContains_Guess() {
    goTo("http://localhost:4567");
    fill("#inputString").with("This is the puzzle text");
    submit(".btn");
    assertThat(pageSource()).contains("Guess:");
  }

  @Test
  public void displayPuzzlePageContains_encryptedString() {
    goTo("http://localhost:4567");
    fill("#inputString").with("This is the puzzle text");
    submit(".btn");
    assertThat(pageSource()).contains("Th-s -s th- p-zzl- t-xt");
  }

  @Test
  public void displayPuzzlePageDoesNotContain_EnterText() {
      goTo("http://localhost:4567/");
      fill("#inputString").with("This is the puzzle text");
      submit(".btn");
      assertThat(pageSource()).doesNotContain("Enter text");
  }
}

  // @Test
  // public void afterBadGuessDisplayPuzzlePageContains_encryptedString() {
  //     goTo("http://localhost:4567/");
  //     // WebDriverWait wait = new WebDriverWait(webDriver, 30);
  //     fill("#inputString").with("This is the puzzle text");
  //     submit(".btn");
  //     // wait.until(elementToBeClickable(By.id("guessInput")));
  //     WebElement guessInput = (new WebDriverWait(webDriver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("#guessInput")));
  //     fill("#guessInput").with("Not a match");
  //     submit(".btn");
  //     assertThat(pageSource()).contains("Th-s -s th- p-zzl- t-xt");
  // }
  //
  // @Test
  // public void afterBadGuessDisplayPuzzlePageDoesNotContain_EnterText() {
  //     goTo("http://localhost:4567/");
  //     webDriver.implicitly_wait(5);
  //     fill("#inputString").with("This is the puzzle text");
  //     submit(".btn");
  //     fill("#guessInput").with("Not a match");
  //     submit(".btn");
  //     assertThat(pageSource()).doesNotContain("Enter text");
  // }
  //
  //
  // @Test
  // public void afterGoodGuessDisplayPuzzlePageContains_originalInput() {
  //     goTo("http://localhost:4567/");
  //     webDriver.implicitly_wait(5);
  //     fill("#inputString").with("This is the puzzle text");
  //     submit(".btn");
  //     fill("#guessInput").with("This is the puzzle text");
  //     submit(".btn");
  //     assertThat(pageSource()).contains("This is the puzzle text");
  // }
  //
  // @Test
  // public void afterGoodGuessDisplayPuzzlePageDoesNotContain_EnterText() {
  //     goTo("http://localhost:4567/");
  //     webDriver.implicitly_wait(5);
  //     fill("#inputString").with("This is the puzzle text");
  //     submit(".btn");
  //     fill("#guessInput").with("Not a match");
  //     submit(".btn");
  //     assertThat(pageSource()).doesNotContain("Enter text");
  // }
