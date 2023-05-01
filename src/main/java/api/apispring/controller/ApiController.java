package api.apispring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {

    @GetMapping("api")
    public String api(Model model){
        model.addAttribute("data", "api!");
        return "api"; //templates 밑에 있는 api.html을 찾아간다.
    }

    @GetMapping("api-mvc")
    public String apiMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "api-template";
    }

    @GetMapping("api-string")
    @ResponseBody
    public String apiString(@RequestParam("name") String name){
        return "hello "+name;
    }

    @GetMapping("api-api")
    @ResponseBody
    public Api apiApi(@RequestParam("name") String name){
        Api api = new Api();
        api.setName(name);
        return api;
    }

    static class Api {
        private String name;

        public void setName(String name) {
            this.name = name;
        }
        public String getName(){
            return name;
        }
    }
}
