package au.edu.unsw.soacourse.soapcurrencyconvert;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-10-05T19:01:55.490+11:00
 * Generated source version: 3.0.4
 * 
 */
@WebServiceClient(name = "SOAPCurrencyConvertService", 
                  wsdlLocation = "file:/Users/Solki/Documents/workspace/SOAPCurrencyConvertService/src/main/resources/wsdl/SOAPCurrencyConvertService.wsdl",
                  targetNamespace = "http://soapcurrencyconvert.soacourse.unsw.edu.au") 
public class SOAPCurrencyConvertService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soapcurrencyconvert.soacourse.unsw.edu.au", "SOAPCurrencyConvertService");
    public final static QName SOAPCurrencyConvertService = new QName("http://soapcurrencyconvert.soacourse.unsw.edu.au", "SOAPCurrencyConvertService");
    static {
        URL url = null;
        try {
            url = new URL("file:/Users/Solki/Documents/workspace/SOAPCurrencyConvertService/src/main/resources/wsdl/SOAPCurrencyConvertService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SOAPCurrencyConvertService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/Users/Solki/Documents/workspace/SOAPCurrencyConvertService/src/main/resources/wsdl/SOAPCurrencyConvertService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SOAPCurrencyConvertService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SOAPCurrencyConvertService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOAPCurrencyConvertService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SOAPCurrencyConvertService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SOAPCurrencyConvertService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SOAPCurrencyConvertService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns SOAPCurrencyConvertService
     */
    @WebEndpoint(name = "SOAPCurrencyConvertService")
    public SOAPCurrencyConvertService getSOAPCurrencyConvertService() {
        return super.getPort(SOAPCurrencyConvertService, SOAPCurrencyConvertService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SOAPCurrencyConvertService
     */
    @WebEndpoint(name = "SOAPCurrencyConvertService")
    public SOAPCurrencyConvertService getSOAPCurrencyConvertService(WebServiceFeature... features) {
        return super.getPort(SOAPCurrencyConvertService, SOAPCurrencyConvertService.class, features);
    }

}
