package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.jsondoc.core.pojo.JSONDoc;
import org.jsondoc.core.scanner.DefaultJSONDocScanner;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Environment;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.Arrays.asList;
import static play.libs.Json.toJson;

@Singleton
public class JsonDocController extends Controller {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Environment env;

    public Result getJsonDoc() {
        return ok(getJsonDocNode());
    }

    private JsonNode getJsonDocNode() {

        // Workaround 1. - Uncomment next line - set correct ClassLoader manually from play environment
        // Thread.currentThread().setContextClassLoader(env.classLoader());

        // Workaround 2. - Revert from AkkaHttp to Netty server

        /*
         * Log cases:
         *  AkkaHttp on Dev (ClassNotFoundException): play-dev-mode-akka.actor.default-dispatcher-2 - class play.runsupport.NamedURLClassLoader
         *  AkkaHttp on Prod (no exceptions, works) : application-akka.actor.default-dispatcher-6 - class sun.misc.Launcher$AppClassLoader
         *  Netty on either (no exceptions, works)  : application-akka.actor.default-dispatcher-4 - class play.runsupport.DelegatedResourcesClassLoader
         */
        log.error(Thread.currentThread().getName() + " - " + Thread.currentThread().getContextClassLoader().getClass().toString());

        final JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
        final JSONDoc jsonDoc = jsondocScanner.getJSONDoc("1.0 ", "/", asList("controllers", "models"), false, JSONDoc.MethodDisplay.URI);

        return toJson(jsonDoc);
    }
}
