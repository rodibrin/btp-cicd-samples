import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;
import groovy.util.XmlParser;
import com.sap.it.api.mapping.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

def Message processData(Message message) {

	def pmap = message.getProperties();
	
	def body = message.getBody(java.lang.String) as String;
	def headers = message.getHeaders() as Map<String, Object>;
	def properties = message.getProperties() as Map<String, Object>;
	
	def propertiesAsString ="\n";
	properties.each{ it -> propertiesAsString = propertiesAsString + "${it}" + "\n" };
	
	def headersAsString ="\n";
	headers.each{ it -> headersAsString = headersAsString + "${it}" + "\n" };
	

	def messageLog = messageLogFactory.getMessageLog(message);
	messageLog.addAttachmentAsString("Posted - Payload" , "\n Properties \n ----------   \n" + propertiesAsString +
	                                                   "\n headers \n ----------   \n" + headersAsString +
	                                                   "\n Body \n ----------  \n\n" + body,
	                                                   "text/xml");

	if(messageLog != null && properties.get("enableLog") == "true") {
// 		messageLog.addAttachmentAsString("Posted - Payload" , "\n Properties \n ----------   \n" + propertiesAsString +
// 		                                                   "\n headers \n ----------   \n" + headersAsString +
// 		                                                   "\n Body \n ----------  \n\n" + body,
// 		                                                   "text/xml");
	}
	return message;
}

// /*
//  The integration developer needs to create the method processData 
//  This method takes Message object of package com.sap.gateway.ip.core.customdev.util 
// which includes helper methods useful for the content developer:
// The methods available are:
//     public java.lang.Object getBody()
// 	public void setBody(java.lang.Object exchangeBody)
//     public java.util.Map<java.lang.String,java.lang.Object> getHeaders()
//     public void setHeaders(java.util.Map<java.lang.String,java.lang.Object> exchangeHeaders)
//     public void setHeader(java.lang.String name, java.lang.Object value)
//     public java.util.Map<java.lang.String,java.lang.Object> getProperties()
//     public void setProperties(java.util.Map<java.lang.String,java.lang.Object> exchangeProperties) 
//     public void setProperty(java.lang.String name, java.lang.Object value)
//     public java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> getSoapHeaders()
//     public void setSoapHeaders(java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> soapHeaders) 
//       public void clearSoapHeaders()
//  */
// import com.sap.gateway.ip.core.customdev.util.Message;
// import java.util.HashMap;
// def Message processData(Message message) {
//     //Body 
//       def body = message.getBody();
//       message.setBody(body + "Body is modified");
//       //Headers 
//       def map = message.getHeaders();
//       def value = map.get("oldHeader");
//       message.setHeader("oldHeader", value + "modified");
//       message.setHeader("newHeader", "newHeader");
//       //Properties 
//       map = message.getProperties();
//       value = map.get("oldProperty");
//       message.setProperty("oldProperty", value + "modified");
//       message.setProperty("newProperty", "newProperty");
//       return message;
// }