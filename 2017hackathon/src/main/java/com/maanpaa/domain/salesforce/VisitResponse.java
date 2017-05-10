
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
"visitWLSRPhone",
"visitStartTime",
"visitName",
"visitId",
"visitEndTime",
"visitDetail",
"visitContactOffice",
"visitContact"
})
public class VisitResponse {

@JsonProperty("visitWLSRPhone")
private String visitWLSRPhone;
@JsonProperty("visitStartTime")
private Object visitStartTime;
@JsonProperty("visitName")
private Object visitName;
@JsonProperty("visitId")
private String visitId;
@JsonProperty("visitEndTime")
private Object visitEndTime;
@JsonProperty("visitDetail")
private String visitDetail;
@JsonProperty("visitContactOffice")
private Object visitContactOffice;
@JsonProperty("visitContact")
private Object visitContact;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("visitWLSRPhone")
public String getVisitWLSRPhone() {
return visitWLSRPhone;
}

@JsonProperty("visitWLSRPhone")
public void setVisitWLSRPhone(String visitWLSRPhone) {
this.visitWLSRPhone = visitWLSRPhone;
}

@JsonProperty("visitStartTime")
public Object getVisitStartTime() {
return visitStartTime;
}

@JsonProperty("visitStartTime")
public void setVisitStartTime(Object visitStartTime) {
this.visitStartTime = visitStartTime;
}

@JsonProperty("visitName")
public Object getVisitName() {
return visitName;
}

@JsonProperty("visitName")
public void setVisitName(Object visitName) {
this.visitName = visitName;
}

@JsonProperty("visitId")
public String getVisitId() {
return visitId;
}

@JsonProperty("visitId")
public void setVisitId(String visitId) {
this.visitId = visitId;
}

@JsonProperty("visitEndTime")
public Object getVisitEndTime() {
return visitEndTime;
}

@JsonProperty("visitEndTime")
public void setVisitEndTime(Object visitEndTime) {
this.visitEndTime = visitEndTime;
}

@JsonProperty("visitDetail")
public String getVisitDetail() {
return visitDetail;
}

@JsonProperty("visitDetail")
public void setVisitDetail(String visitDetail) {
this.visitDetail = visitDetail;
}

@JsonProperty("visitContactOffice")
public Object getVisitContactOffice() {
return visitContactOffice;
}

@JsonProperty("visitContactOffice")
public void setVisitContactOffice(Object visitContactOffice) {
this.visitContactOffice = visitContactOffice;
}

@JsonProperty("visitContact")
public Object getVisitContact() {
return visitContact;
}

@JsonProperty("visitContact")
public void setVisitContact(Object visitContact) {
this.visitContact = visitContact;
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


