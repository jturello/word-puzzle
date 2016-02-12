import org.junit.*;
import static org.junit.Assert.*;

public class AppTestUnit {

  @Test
  public void vowelsToDash_inputStringHasLowerCaseVowels_aeiou_vowelsChangedToDashes() {
    App app = new App();
    assertEquals("Th-s -s - str-ng w-th v-w-ls - -----", app.vowelsToDash("This is a string with vowels - aeiou"));
  }

  @Test
  public void vowelsToDash_inputStringHasUpperCaseVowels_AEIOU_vowelsChangedToDashes() {
    App app = new App();
    assertEquals("TH-S -S - STR-NG W-TH V-W-LS - -----", app.vowelsToDash("THIS IS A STRING WITH VOWELS - AEIOU"));
  }

}
