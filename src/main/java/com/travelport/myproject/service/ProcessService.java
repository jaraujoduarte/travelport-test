package com.travelport.myproject.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.springframework.stereotype.Service;

import com.travelport.myproject.AppUtils;
import com.travelport.myproject.model.AirSegmentDto;
import com.travelport.myproject.model.City;
import com.travelport.myproject.model.LowFareSearchDto;
import com.travelport.myproject.model.PricingSolutionDto;
import com.travelport.schema.air_v39_0.AirPricingSolution;
import com.travelport.schema.air_v39_0.AirSegmentRef;
import com.travelport.schema.air_v39_0.LowFareSearchRsp;
import com.travelport.schema.air_v39_0.TypeBaseAirSegment;

/*
 * Service to orchestrate, obtain and prepare the information 
 */
@Service
public class ProcessService {

    private static LowFareSearchRsp sResponseObject;
    private Map<String, TypeBaseAirSegment> segmentMap;

    public ProcessService() {
        segmentMap = new HashMap<String, TypeBaseAirSegment>();
        sResponseObject = parseResponseXml();
    }

    /**
     * Method to obtain, parse, and strip down the search response
     * 
     * @return LowFareSearchDto object
     */
    public LowFareSearchDto processSearchResponse() {
        LowFareSearchDto result = new LowFareSearchDto();

        City origin = AppUtils.getCityFromCode(sResponseObject.getRouteList().getRoute().get(0).getLeg().get(0).getOrigin());
        result.setOrigin(origin);

        City destination = AppUtils.getCityFromCode(sResponseObject.getRouteList().getRoute().get(0).getLeg().get(0).getDestination());
        result.setDestination(destination);

        // Create a map for the segments
        for (TypeBaseAirSegment segment : sResponseObject.getAirSegmentList().getAirSegment()) {
            segmentMap.put(segment.getKey(), segment);
        }

        List<PricingSolutionDto> princingSolDtoList = new ArrayList<PricingSolutionDto>();
        for (AirPricingSolution pricingSol : sResponseObject.getAirPricingSolution()) {

            PricingSolutionDto pricingSolDto = new PricingSolutionDto();
            pricingSolDto.setPrice(pricingSol.getApproximateTotalPrice());

            List<AirSegmentDto> airSegmentDtoList = new ArrayList<AirSegmentDto>();
            for (AirSegmentRef airSegmentRef : pricingSol.getAirSegmentRef()) {
                TypeBaseAirSegment mappedSegment = segmentMap.get(airSegmentRef.getKey());
                AirSegmentDto segmentDto = new AirSegmentDto();

                segmentDto.setGroup(mappedSegment.getGroup());
                segmentDto.setCarrier(AppUtils.getAirlineFromCode(mappedSegment.getCarrier()));
                segmentDto.setEquipement(AppUtils.getAirplaneFromCode(mappedSegment.getEquipment()));
                segmentDto.setFlightNumber(mappedSegment.getFlightNumber());
                segmentDto.setFlightTime(mappedSegment.getFlightTime().toString());
                segmentDto.setArrivalTime(mappedSegment.getArrivalTime());
                segmentDto.setDepartureTime(mappedSegment.getDepartureTime());

                airSegmentDtoList.add(segmentDto);
            }

            pricingSolDto.setAirSegmentList(airSegmentDtoList);
            princingSolDtoList.add(pricingSolDto);
        }

        result.setPricingSolutionList(princingSolDtoList);

        return result;
    }

    /**
     * Creates a LowFareSearchRsp client object based on the soap response
     * 
     * @return
     */
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
