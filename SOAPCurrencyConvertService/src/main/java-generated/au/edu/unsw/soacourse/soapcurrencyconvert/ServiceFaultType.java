
package au.edu.unsw.soacourse.soapcurrencyconvert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceFaultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceFaultType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="faultType" type="{http://soapcurrencyconvert.soacourse.unsw.edu.au}ExchangeRateFaultType"/&gt;
 *         &lt;element name="faultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceFaultType", propOrder = {
    "faultType",
    "faultMessage"
})
public class ServiceFaultType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ExchangeRateFaultType faultType;
    @XmlElement(required = true)
    protected String faultMessage;

    /**
     * Gets the value of the faultType property.
     * 
     * @return
     *     possible object is
     *     {@link ExchangeRateFaultType }
     *     
     */
    public ExchangeRateFaultType getFaultType() {
        return faultType;
    }

    /**
     * Sets the value of the faultType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangeRateFaultType }
     *     
     */
    public void setFaultType(ExchangeRateFaultType value) {
        this.faultType = value;
    }

    /**
     * Gets the value of the faultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultMessage() {
        return faultMessage;
    }

    /**
     * Sets the value of the faultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultMessage(String value) {
        this.faultMessage = value;
    }

}
