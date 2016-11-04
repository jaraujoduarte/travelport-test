package com.travelport.myproject.service;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.springframework.stereotype.Service;

import com.travelport.myproject.AppUtils;
import com.travelport.schema.air_v39_0.LowFareSearchRsp;

@Service
public class ProcessService {

    private static LowFareSearchRsp sResponseObject;

    public ProcessService() {
        sResponseObject = parseResponseXml();

    }

    public String getSearchResponse() {
        return "this comes from my process " + sResponseObject.getFlightDetailsList().getFlightDetails().get(0).getArrivalTime();
    }

    public String myProcess() {
        return "this comes from my process ";
    }

    private LowFareSearchRsp parseResponseXml() {
        try {
            String rspString = AppUtils.stringFromFile(AppUtils.getProperty("myproject.airresponse.path"));
            SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(rspString.getBytes()));
            Unmarshaller unmarshaller = JAXBContext.newInstance(LowFareSearchRsp.class).createUnmarshaller();
            LowFareSearchRsp obj = (LowFareSearchRsp) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
