import com.sap.it.api.mapping.*;

def String getCustomerId(String header,MappingContext context)
{
    return context.getProperty("customerId").toString();
}

def String getUUID(String header,MappingContext context)
{
    return context.getProperty("uuid").toString();
}

def String getLeadingSystemCustomerId(String header,MappingContext context)
{
    return context.getProperty("LeadingSystemCustomerId").toString();
}
