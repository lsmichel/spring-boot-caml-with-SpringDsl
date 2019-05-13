/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.atos.projet.tigo.cameltuto.rest.api;

import javax.ws.rs.core.MediaType;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import sn.atos.projet.tigo.cameltuto.ExampleServices;
import sn.atos.projet.tigo.cameltuto.beans.MyBean;

/**
 *
 * @author A746054
 */
 @Component
public class RestApi extends RouteBuilder{
    String serverPort="8080";
    String contextPath="/camel";
    @Override
        public void configure() {
            CamelContext context = new DefaultCamelContext();
            
            restConfiguration().contextPath(contextPath) //
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .apiProperty("cors", "true") // cross-site
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");
            
            rest("/api/").description("Teste REST Service")
                .id("api-route")
                .post("/bean")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .bindingMode(RestBindingMode.auto)
                .type(MyBean.class)
                .enableCORS(true)
                .to("direct:remoteService");
        }
}
