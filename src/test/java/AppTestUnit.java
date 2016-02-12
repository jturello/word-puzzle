import org.junit.*;
import static org.junit.Assert.*;

public class AppTestUnit {

  @Test
  public void vowelsToDash_inputStringHasLowerCaseVowels_aeiou_vowelsChangedToDashes() {
    App app = new App();
    assertEquals("Th-s -s - str-ng w-th v-w-ls - ----- -r -----", app.vowelsToDash("This is a string with vowels - aeiou or AEIOU"));
  }

}
