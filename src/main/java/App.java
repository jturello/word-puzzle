import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;


public class App {



  public static void main( String[] args ) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap model = new HashMap();

      model.put("simpleHeading", "Simple Input Form");
      model.put("template", "templates/simple-form.vtl");
      return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());

    get("/result-simple", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/simple-form.vtl");
      String simpleInput = request.queryParams("userInput");

      model.put("simpleHeading", "Result Below:");
      model.put("simpleInput", simpleInput);
      return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());


    get("/greeting-form", (request, response) -> {
      HashMap model = new HashMap();

      model.put("template", "templates/greeting-form.vtl" );
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/greeting_card", (request, response) -> {
      HashMap model = new HashMap();
      String recipient = request.queryParams("recipient");
      String sender = request.queryParams("sender");

      model.put("recipient", recipient);
      model.put("sender", sender);
      model.put("template", "templates/greeting_card.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/leap-year", (request, response) -> {
      HashMap model = new HashMap();

      model.put("simpleHeading", "Leap-year Input Form");
      model.put("template", "templates/simple-form.vtl");
      return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());


    get("/result-leap", (request, response) -> {
      HashMap model = new HashMap();
      String yearInput = request.queryParams("userInput");

      model.put("simpleHeading", "Leap-year Result Below:");
      model.put("yearInput", yearInput);
      model.put("template", "templates/simple-form.vtl");
      return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());

  }

}
