import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {

	def pmap = message.getProperties();

	def body = message.getBody(java.lang.String) as String;
	def headers = message.getHeaders() as Map<String, Object>;
	def properties = message.getProperties() as Map<String, Object>;

	def messageLog = messageLogFactory.getMessageLog(message);
	def logStep=properties.get("loggingStep");
    if(messageLog != null && properties.get("enableLog") == "true"){
	messageLog.addAttachmentAsString(logStep, "\n Properties \n ----------   \n"  + properties +
		                                                   "\n\n Headers \n ----------   \n"  + headers +
		                                                   "\n\n Body \n ----------  \n" + body,
		                                                   "text/xml");
	}


       return message;
}
