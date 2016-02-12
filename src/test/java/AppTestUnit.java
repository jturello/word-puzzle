import org.junit.*;
import static org.junit.Assert.*;

public class AppTestUnit {

  @Test
  public void vowelsToDash_inputStringHasLowerCaseVowels_aeiou_vowelsChangedToDashes() {
    App app = new App();
    assertEquals("Th-s -s - str-ng w-th v-w-ls - -----", app.vowelsToDash("This is a string with vowels - aeiou"));
  }

  @Test
  public void vowelsToDash_inputStringHas_y_asVowel_Y_asVowelsChangedToDash() {
    App app = new App();
    assertEquals("TH-S -S - STR-NG W-TH V-W-LS - -----", app.vowelsToDash("THIS IS A STRING WITH VOWELS - AEIOU"));
  }

  @Test
  public void vowelsToDash_convertYifNotFollowedByVowel_YsemiVowelchangesToDash() {
    App app = new App();
    assertEquals("Y- fr-y--. Y'-ll n--d t---b--ts. -v-y-, J--'s y-ll-w y-tch -nd tw-tch- t-- d-g -r- y--rl-", app.vowelsToDash("Yo froyoy. Y'all need toy-boats. Avaya, Joy's yellow yatch and twitchy toy dog are yearly"));
  }

  @Test
  public void vowelsToDash_convertYbeforeApostropheOnlyIfApostropheNotFollowedByVowel_yBeforeApostropheAllNotConverted() {
    App app = new App();
    assertEquals("Y- fr-y--. Y'-ll n--d t---b--ts. -v-y-, J--'s y-ll-w y-tch -nd tw-tch- t-- d-g -r- y--rl-", app.vowelsToDash("Yo froyoy. Y'all need toy-boats. Avaya, Joy's yellow yatch and twitchy toy dog are yearly"));
  }}
//
// "Y- h-- fr-y-y- -s J--'s gr---y-ll-w t-- y-ck"
//
// "Yo froyoy. Y'all need toy-boats. Avaya, Joy's yellow yatch and twitchy toy dog are yearly"
// "Y- fr-y--. Y'-ll n--d t---b--ts. -v-y-, J--'s y-ll-w y-tch -nd tw-tch- t-- d-g -r- y--rl-"
