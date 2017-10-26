package controllers;

import org.jsondoc.core.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import static org.jsondoc.core.pojo.ApiVerb.GET;

public class FooController extends Controller {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiMethod(path = {"/"}, verb = GET, produces = "json", description = "Retrieve a list of foos.")
    @ApiResponseObject(clazz = String[].class)
    @ApiHeaders(headers = {
            @ApiHeader(name = "123", description = "Number")
    })
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "keys", description = "List of foo keys", clazz = String.class, required = false),
            @ApiQueryParam(name = "name", description = "List of foo names", clazz = String.class, required = false),
    })
    public Result index() {
        log.error(Thread.currentThread().getName() + " - " + Thread.currentThread().getContextClassLoader().getClass().toString());
        return ok(index.render("Your new application is ready."));
    }

}
