package com.gw.steel.steel.util.common;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils {
    private static final Log       logger          = LogFactory.getLog(XmlUtils.class);

    private static final String    DEFAULT_CHARSET = "UTF-8";

    private static final XmlMapper mapper          = new XmlMapper();

    private XmlUtils() {
    }

    public static String object2Xml(Object object) throws IOException {
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    public static <T> T xml2Object(String xml, Class<T> clz) throws IOException {
        return mapper.readValue(xml, clz);
    }

    public static String object2XmlByJAXB(Object object) {
        return object2XmlByJAXB(object, DEFAULT_CHARSET, false, false);
    }

    public static String object2XmlByJAXB(Object object, String charset, Boolean format,
                                          Boolean fragment) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            // create marshaller
            Marshaller marshaller = context.createMarshaller();
            // specify the output encoding in the marshalled XML data.
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charset);
            // specify whether or not the marshalled XML data is formatted with linefeeds and indentation
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
            // specify whether or not the marshaller will generate declaration region into XML data
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);

            marshaller.marshal(object, writer);

        } catch (Exception e) {
            logger.error("Fail to convert object[" + object + "] to XML", e);
        }
        return writer.toString();
    }

    @SuppressWarnings("all")
    public static <T> T xml2ObjectByJAXB(String object, Class<T> clz) {
        T result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clz);
            // create unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(new StringReader(object));
        } catch (Exception e) {
            logger.error("Fail to convert xml[" + object + "] to object[" + clz + "]", e);
        }
        return result;
    }

}
