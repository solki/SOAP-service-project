package au.edu.unsw.assi.soapservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-09-01T16:16:27.767+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebServiceClient(name = "Service1ImplService", 
                  wsdlLocation = "http://localhost:8080/ImportDownloadService/Service1?wsdl",
                  targetNamespace = "http://soapservice.assi.unsw.edu.au/") 
public class Service1ImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soapservice.assi.unsw.edu.au/", "Service1ImplService");
    public final static QName Service1ImplPort = new QName("http://soapservice.assi.unsw.edu.au/", "Service1ImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/ImportDownloadService/Service1?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Service1ImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/ImportDownloadService/Service1?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Service1ImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Service1ImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Service1ImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Service1ImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Service1ImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Service1ImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns Service1
     */
    @WebEndpoint(name = "Service1ImplPort")
    public Service1 getService1ImplPort() {
        return super.getPort(Service1ImplPort, Service1.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Service1
     */
    @WebEndpoint(name = "Service1ImplPort")
    public Service1 getService1ImplPort(WebServiceFeature... features) {
        return super.getPort(Service1ImplPort, Service1.class, features);
    }

}