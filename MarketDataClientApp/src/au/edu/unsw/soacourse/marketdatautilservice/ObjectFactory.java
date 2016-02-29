
package au.edu.unsw.soacourse.marketdatautilservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the au.edu.unsw.soacourse.marketdatautilservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConvertMarketDataFault_QNAME = new QName("http://marketdatautilservice.soacourse.unsw.edu.au", "convertMarketDataFault");
    private final static QName _SummaryMarketDataFault_QNAME = new QName("http://marketdatautilservice.soacourse.unsw.edu.au", "summaryMarketDataFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: au.edu.unsw.soacourse.marketdatautilservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceFaultType }
     * 
     */
    public ServiceFaultType createServiceFaultType() {
        return new ServiceFaultType();
    }

    /**
     * Create an instance of {@link ConvertMarketDataRequest }
     * 
     */
    public ConvertMarketDataRequest createConvertMarketDataRequest() {
        return new ConvertMarketDataRequest();
    }

    /**
     * Create an instance of {@link ConvertMarketDataResponse }
     * 
     */
    public ConvertMarketDataResponse createConvertMarketDataResponse() {
        return new ConvertMarketDataResponse();
    }

    /**
     * Create an instance of {@link SummaryMarketDataRequest }
     * 
     */
    public SummaryMarketDataRequest createSummaryMarketDataRequest() {
        return new SummaryMarketDataRequest();
    }

    /**
     * Create an instance of {@link SummaryMarketDataResponse }
     * 
     */
    public SummaryMarketDataResponse createSummaryMarketDataResponse() {
        return new SummaryMarketDataResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketdatautilservice.soacourse.unsw.edu.au", name = "convertMarketDataFault")
    public JAXBElement<ServiceFaultType> createConvertMarketDataFault(ServiceFaultType value) {
        return new JAXBElement<ServiceFaultType>(_ConvertMarketDataFault_QNAME, ServiceFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketdatautilservice.soacourse.unsw.edu.au", name = "summaryMarketDataFault")
    public JAXBElement<ServiceFaultType> createSummaryMarketDataFault(ServiceFaultType value) {
        return new JAXBElement<ServiceFaultType>(_SummaryMarketDataFault_QNAME, ServiceFaultType.class, null, value);
    }

}
