
package seatreservation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetSeatStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="GetSeatStatusResponse">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="GetSeatStatusResult" type="{http://www.iit.bme.hu/soi/hw/SeatReservation}SeatStatus"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSeatStatusResponse", propOrder = {
    "getSeatStatusResult"
})
public class GetSeatStatusResponse {

    @XmlElement(name = "GetSeatStatusResult", required = true)
    @XmlSchemaType(name = "string")
    protected SeatStatus getSeatStatusResult;

    /**
     * Gets the value of the getSeatStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link SeatStatus }
     *     
     */
    public SeatStatus getGetSeatStatusResult() {
        return getSeatStatusResult;
    }

    /**
     * Sets the value of the getSeatStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeatStatus }
     *     
     */
    public void setGetSeatStatusResult(SeatStatus value) {
        this.getSeatStatusResult = value;
    }

}
