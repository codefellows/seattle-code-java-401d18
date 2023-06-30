package com.piffner.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * `@Controller` tells spring to load this class as a controller and look for routes
 * `@RequestMapping` tells spring the path for the controller (prefix of the individual routes)
 */
@Controller
@RequestMapping("/hellocontroller")
public class HelloController {

    /**
     * RequestMapping maps a path to a method.
     * RequestMethods can get GET, POST, UPDATE, etc.
     * <p>
     * ResponseBody tells spring that the contents of return value should be sent
     * to the requester.
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getTest() {
        return "Testing.";
    }

    /**
     * GetMapping("/mypath") is a shortcut @RequestMapping(value = "/mypath", method = RequestMethod.GET)
     *
     * @return
     */
    @GetMapping("/hello")
    @ResponseBody
    public String getHello() {
        return "hi.";
    }

    /**
     * http://localhost:8080/hellocontroller/sayhello/{name}
     * <p>
     * To get a Path parameter (one embedded in the request path)
     * You use {variableName} syntax.
     * <p>
     * It will be parsed by spring and passed in to the matching PathVariable
     *
     * @param name
     * @return
     */
    @GetMapping("/sayhello/{name}")
    @ResponseBody
    public String getNamedHello(@PathVariable String name) {
        String greeting = "Hello, " + name;
        return greeting;
    }

    /**
     * http://localhost:8080/hellocontroller/sayhello/{name}?greeting={greeting}
     * <p>
     * by adding a @RequestParam we can look for parameters on the query string.
     * In this case, we set it as required = false (by default it's true)
     * and set a default value
     *
     * @param name
     * @param greeting
     * @return
     */
    @GetMapping("/greetings/{name}")
    @ResponseBody
    public String getGreetingForName(
            @PathVariable String name,
            @RequestParam(required = false, defaultValue = "Hello, ") String greeting
    ) {
        String results = greeting + name;
        return results;
    }


    /**
     * If there is no @ResponseBody annotation, then the default behavior happens:
     * It returns a template
     * <p>
     * PathVariables and RequestParams are still available.
     * <p>
     * But also the "Model" which is just a bag to put attributes to be used by the view.
     *
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/complex/{name}")
    public String moreComplex(
            @PathVariable String name,
            Model model
    ) {
        Information info = new Information();
        info.name = name;
        info.number = 7;

        // info.name and info.number will be available in the template
        model.addAttribute("info", info);
        return "complex";
    }
}
