
package com.techiekernel.ws.jaxws.document.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.techiekernel.ws.jaxws.document.Server;

@XmlRootElement(name = "getServerDetailResponse", namespace = "http://document.jaxws.ws.techiekernel.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getServerDetailResponse", namespace = "http://document.jaxws.ws.techiekernel.com/")
public class GetServerDetailResponse {

    @XmlElement(name = "return", namespace = "")
    private Server _return;

    /**
     * 
     * @return
     *     returns Server
     */
    public Server getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Server _return) {
        this._return = _return;
    }

}
