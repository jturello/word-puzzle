import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;


public class App {

    public static String inputStr = "";
    public static String encryptedStr = "";
    public static String guess = "";

  public static String vowelsToDash(String inputString) {
    inputStr = inputString;
    // convert 'y' when acting as a vowel & lower- and uppercase vowels aeiou to dashes
    encryptedStr = inputStr.replaceAll("y(?![aeiouAEIOU])(?!'[aeiou])|([aeiouAEIOU])", "-");

    return encryptedStr;
  }

  public static boolean compareToInput(String userGuess) {
    guess = userGuess;

    return inputStr.equals(guess);
  }


  public static void main( String[] args ) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap model = new HashMap();

      model.put("template", "templates/start-game-form.vtl");
      return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());


    get("/generate-puzzle", (request, response) -> {
     HashMap model = new HashMap();

       String encryptedString = vowelsToDash(request.queryParams("inputString"));

       model.put("encryptedString", encryptedString);
       model.put("template", "templates/display-puzzle.vtl");
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/guess", (request, response) -> {
      HashMap model = new HashMap();

      boolean isMatch = compareToInput(request.queryParams("guess"));
      String encryptedString = request.queryParams("puzzle-string");

      if (isMatch) {
        model.put("isMatch", "True");
        // model.put("result", "Is match");
        model.put("encryptedString", inputStr);
        model.put("message", "Your guess was a match!");
      } else {
        model.put("noMatch", "True");
        // model.put("result", "matching");
        model.put("encryptedString", encryptedString);
        model.put("message", "Not a match. Try again.");
        // model.put("encryptedStr", encryptedStr);
      }

      // model.put("encryptedStr", encryptedStr);
      model.put("template", "templates/display-puzzle.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  //
  //   get("/result-simple", (request, response) -> {
  //     HashMap model = new HashMap();
  //     model.put("template", "templates/simple-form.vtl");
  //     String simpleInput = request.queryParams("userInput");
  //
  //     model.put("simpleHeading", "Result Below:");
  //     model.put("simpleInput", simpleInput);
  //     return new ModelAndView(model, layout);
  //      }, new VelocityTemplateEngine());
  //
  //
    // get("/greeting-form", (request, response) -> {
    //   HashMap model = new HashMap();
    //
    //   model.put("template", "templates/greeting-form.vtl" );
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
  //
  //
  //   get("/greeting_card", (request, response) -> {
  //     HashMap model = new HashMap();
  //     String recipient = request.queryParams("recipient");
  //     String sender = request.queryParams("sender");
  //
  //     model.put("recipient", recipient);
  //     model.put("sender", sender);
  //     model.put("template", "templates/greeting_card.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  // //
  // //
  //   get("/leap-year", (request, response) -> {
  //     HashMap model = new HashMap();
  //
  //     model.put("simpleHeading", "Leap-year Input Form");
  //     model.put("template", "templates/simple-form.vtl");
  //     return new ModelAndView(model, layout);
  //      }, new VelocityTemplateEngine());
  //
  //
  //   get("/result-leap", (request, response) -> {
  //     HashMap model = new HashMap();
  //     String yearInput = request.queryParams("userInput");
  //
  //     model.put("simpleHeading", "Leap-year Result Below:");
  //     model.put("yearInput", yearInput);
  //     model.put("template", "templates/simple-form.vtl");
  //     return new ModelAndView(model, layout);
  //      }, new VelocityTemplateEngine());
  }

}
