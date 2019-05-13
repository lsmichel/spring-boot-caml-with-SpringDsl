/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.atos.projet.tigo.cameltuto.processor;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import sn.atos.projet.tigo.cameltuto.message.sendInitialMessageToQueResponse;

/**
 *
 * @author A746054
 */
@Component
public class Producer extends RouteBuilder{
     @Override
        public void configure() {
            CamelContext context = new DefaultCamelContext();
            from("direct:remoteService")
                .routeId("direct-route")
                .marshal()
                .json(JsonLibrary.Jackson)
                .choice()
                    .when()
                    .jsonpath("$..[?(@.id > 1)]")
                    .inOnly("activemq:queue:example.bean1")
                    .log(">>> processed")
                    .process(new Processor() {
                         @Override
                      public void process(Exchange exchange) throws Exception {
                         sendInitialMessageToQueResponse _res = new sendInitialMessageToQueResponse();
                         _res.setHasError(Boolean.FALSE);
                         _res.setErrorMessage("message processing sucessfull");
                         _res.setCamelProcessiingErrorMessage("");
                         _res.setCamelProcessiingError(Boolean.FALSE);
                         _res.setMessageProcesseced(Boolean.TRUE);
                         exchange.getOut().setBody(_res);
                      }
                   })
                    //.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
                .otherwise()
                    .process(new Processor() {
                         @Override
                      public void process(Exchange exchange) throws Exception {
                         sendInitialMessageToQueResponse _res = new sendInitialMessageToQueResponse();
                         _res.setHasError(Boolean.TRUE);
                         _res.setErrorMessage("message id must be rather than 1");
                         _res.setCamelProcessiingErrorMessage("");
                         _res.setCamelProcessiingError(Boolean.FALSE);
                         _res.setMessageProcesseced(Boolean.TRUE);
                         exchange.getOut().setBody(_res);
                      }
                   })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
        }  
}
