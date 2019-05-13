/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.atos.projet.tigo.cameltuto.processor;

import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import sn.atos.projet.tigo.cameltuto.beans.MyBean;
import sn.atos.projet.tigo.cameltuto.beans.Mybean2;

/**
 *
 * @author A746054
 */
@Component
public class Consumer extends RouteBuilder{
    @Override
    public void configure() throws Exception {
            //CamelContext context = new DefaultCamelContext();
            from("activemq:queue:example.bean1")
            .unmarshal()
            .json(JsonLibrary.Jackson, MyBean.class)
             .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MyBean _receptionedBean = (MyBean)  exchange.getIn().getBody();
                        Mybean2 _transformedBean = new Mybean2();
                        Date date = new Date() ;
                        _transformedBean.setId(_receptionedBean.getId()+date.hashCode());
                        _transformedBean.setName(_receptionedBean.getName()+"--"+date.toString());
                        _transformedBean.setPlateform("Default");
                        _transformedBean.setReceptionDate(date);
                        exchange.getOut().setBody(_transformedBean);
                    }
                })
                .marshal()
                .json(JsonLibrary.Jackson)
                .inOnly("activemq:queue:example.bean2")
                .log(">>> processed") 
                .end();  
        }
}
