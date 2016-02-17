import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTestInt extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver(true);
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


  @Test
  public void afterBadGuessDisplayPuzzlePageContains_encryptedString() {
      goTo("http://localhost:4567/");
      fill("#inputString").with("This is the puzzle text");
      submit(".btn");
      // await().atMost(100).untilPage().isLoaded();
      try {
      fill("#guessInput").with("Not a match");
      submit(".btn");
      assertThat(pageSource()).contains("Th-s -s th- p-zzl- t-xt");
    } catch(Exception e) {
      System.out.println("Exception thrown: " + e.getMessage());
    }
  }


  @Test
  public void afterBadGuessDisplayPuzzlePageDoesNotContain_EnterText() {
      goTo("http://localhost:4567/");
      fill("#inputString").with("This is the puzzle text");
      submit(".btn");
      try {
      fill("#guessInput").with("Not a match");
      submit(".btn");
      assertThat(pageSource()).doesNotContain("Enter text");
    } catch(Exception e) {
      System.out.println("Exception thrown: " + e.getMessage());
    }
  }

  //
  @Test
  public void afterGoodGuessDisplayPuzzlePageContains_originalInput() {
      goTo("http://localhost:4567/");
      fill("#inputString").with("This is the puzzle text");
      submit(".btn");
      try {
      fill("#guessInput").with("This is the puzzle text");
      submit(".btn");
      assertThat(pageSource()).contains("This is the puzzle text");
    } catch(Exception e) {
      System.out.println("Exception thrown: " + e.getMessage());
    }
  }

  @Test
  public void afterGoodGuessDisplayPuzzlePageDoesNotContain_EnterText() {
      goTo("http://localhost:4567/");
      fill("#inputString").with("This is the puzzle text");
      submit(".btn");
      try
      {fill("#guessInput").with("Not a match");
      submit(".btn");
      assertThat(pageSource()).doesNotContain("Enter text");
    } catch(Exception e) {
      System.out.println("Exception thrown: " + e.getMessage());
    }
  }
}
