import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    /******************************************************
      Students: TODO: Display all clients on main page
    *******************************************************/
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputtedStylist = request.queryParams("stylist");
      Stylist newStylist = new Stylist(inputtedStylist);
      newStylist.save();
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputtedName = request.queryParams("name");
      int inputtedStylistId = Integer.parseInt(request.queryParams("stylist_id"));

      Client newClient = new Client(inputtedName, inputtedStylistId);
      newClient.save();

      model.put("inputtedStylistId", inputtedStylistId);
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));

      model.put("client", client);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id/updateName", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      String inputtedName = request.queryParams("name");
      client.updateName(inputtedName);

      model.put("client", client);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id/updateStylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      int inputtedStylistId = Integer.parseInt(request.queryParams("stylist_id"));
      client.updateStylist(inputtedStylistId);

      model.put("client", client);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      client.delete();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/updateName", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String inputtedName = request.queryParams("name");
      stylist.updateName(inputtedName);
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      stylist.delete();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
