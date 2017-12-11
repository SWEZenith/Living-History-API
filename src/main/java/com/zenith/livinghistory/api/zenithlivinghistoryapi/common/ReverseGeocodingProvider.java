package com.zenith.livinghistory.api.zenithlivinghistoryapi.common;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ReverseGeocodingProvider {

    //region Private Members

    private static String baseUri = "http://maps.googleapis.com/maps/api/geocode/xml?latlng=%1$s,%2$s&sensor=false";

    //endregion

    //region Public Methods

    public List<String> getPlaceNamesofLocation(Double latitude, Double longitude){

        List<String> placeNames = new ArrayList<>();

        try {

            String targetURL = String.format(baseUri, latitude, longitude);
            URL url = new URL(targetURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (200 == connection.getResponseCode()) {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer responseBody = new StringBuffer();


                while ((line = bufferedReader.readLine()) != null) {
                    responseBody.append(line);
                }
                bufferedReader.close();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;

                builder = factory.newDocumentBuilder();
                Document xmlBody = builder.parse(new InputSource(new StringReader(responseBody.toString())));
                NodeList shortNames = xmlBody.getElementsByTagName("short_name");

                for (int i = 0; i < shortNames.getLength(); i++)
                {
                    Node shortNameNode = shortNames.item(i);
                    Element shortNameElement = (Element) shortNameNode;
                    String shortNameContent = shortNameElement.getTextContent();

                    if (!placeNames.contains(shortNameContent) && shortNameContent.length() > 2)
                        placeNames.add(shortNameContent);
                }
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        }

        return  placeNames;
    }


    //endregion


}
