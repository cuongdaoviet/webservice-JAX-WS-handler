package com.techiekernel.ws.jaxws.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

public class AuthenticationHandler implements SOAPHandler<SOAPMessageContext> {

	public boolean handleMessage(SOAPMessageContext context) {

		System.out.println("handleMessage() called.");

		Boolean isRequest = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		// for request message only, true for outbound messages, false for
		// inbound
		if (!isRequest) {

			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				// if no header, add one
				if (soapHeader == null) {
					soapHeader = soapEnv.addHeader();
					// No header found, throw exception
					embedSOAPException(soapMsg, "Missing Header", "ERR:001");
				}

				// Get user Id and password from SOAP header
				Iterator<Node> it = soapHeader
						.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

				// if no header block for next actor found? throw exception
				if (it == null || !it.hasNext()) {
					embedSOAPException(soapMsg, "No Header Data", "ERR:002");
				}

				String userName = null;
				String password = null;

				while (it.hasNext()) {
					Node node = it.next();
					if (node != null && node.getNodeName().equals("userName"))
						userName = node.getValue();
					if (node != null && node.getNodeName().equals("password"))
						password = node.getValue();
				}

				if (userName == null || password == null) {
					embedSOAPException(soapMsg, "No userName or password.",
							"ERR:003");
				}

				// the auth code
				if (!userName.equals("foo") || !password.equals("bar")) {
					embedSOAPException(soapMsg, "Authentication Failed",
							"ERR:004");
				}

				// tracking
				soapMsg.writeTo(System.out);

			} catch (SOAPException e) {
				System.err.println(e);
				return false;
			} catch (IOException e) {
				System.err.println(e);
				return false;
			}

		}

		// continue other handler chain
		return true;
	}

	public boolean handleFault(SOAPMessageContext context) {

		System.out.println("handleFault() called.");

		return true;
	}

	public void close(MessageContext context) {
		System.out.println("close() called");
	}

	public Set<QName> getHeaders() {
		System.out.println("getHeaders() called");
		return null;
	}

	private void embedSOAPException(SOAPMessage msg, String reason,
			String faultCode) throws SOAPException, IOException {
		SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
		SOAPFault soapFault = soapBody.getFault();
		if (soapFault == null)
			soapFault = soapBody.addFault();
		soapFault.setFaultString(reason);
		soapFault.setFaultCode(faultCode);
		msg.writeTo(System.out);
	}

}
