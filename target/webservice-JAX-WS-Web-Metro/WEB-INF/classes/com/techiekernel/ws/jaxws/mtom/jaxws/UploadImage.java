
package com.techiekernel.ws.jaxws.mtom.jaxws;

import java.awt.Image;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "uploadImage", namespace = "http://mtom.jaxws.ws.techiekernel.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadImage", namespace = "http://mtom.jaxws.ws.techiekernel.com/", propOrder = {
    "arg0",
    "arg1"
})
public class UploadImage {

    @XmlElement(name = "arg0", namespace = "")
    private Image arg0;
    @XmlElement(name = "arg1", namespace = "")
    private String arg1;

    /**
     * 
     * @return
     *     returns Image
     */
    public Image getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Image arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

}
