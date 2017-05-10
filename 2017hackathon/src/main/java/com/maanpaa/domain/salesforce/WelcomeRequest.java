
package com.maanpaa.domain.salesforce;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"txtMessage",
"taskType",
"contactPhoneNumber"
})
public class WelcomeRequest {

@JsonProperty("txtMessage")
private String txtMessage;
@JsonProperty("taskType")
private String taskType;
@JsonProperty("contactPhoneNumber")
private String contactPhoneNumber;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("txtMessage")
public String getTxtMessage() {
return txtMessage;
}

@JsonProperty("txtMessage")
public void setTxtMessage(String txtMessage) {
this.txtMessage = txtMessage;
}

@JsonProperty("taskType")
public String getTaskType() {
return taskType;
}

@JsonProperty("taskType")
public void setTaskType(String taskType) {
this.taskType = taskType;
}

@JsonProperty("contactPhoneNumber")
public String getContactPhoneNumber() {
return contactPhoneNumber;
}

@JsonProperty("contactPhoneNumber")
public void setContactPhoneNumber(String contactPhoneNumber) {
this.contactPhoneNumber = contactPhoneNumber;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}

