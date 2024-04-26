
package seatreservation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LockResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="LockResponse">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="LockResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LockResponse", propOrder = {
    "lockResult"
})
public class LockResponse {

    @XmlElement(name = "LockResult", required = true, nillable = true)
    protected String lockResult;

    /**
     * Gets the value of the lockResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockResult() {
        return lockResult;
    }

    /**
     * Sets the value of the lockResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockResult(String value) {
        this.lockResult = value;
    }

}
