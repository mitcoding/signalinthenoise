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
"visitId",
"visitNotes"
})
public class NotesFromVisitRequest {

@JsonProperty("visitId")
private String visitId;
@JsonProperty("visitNotes")
private String visitNotes;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("visitId")
public String getVisitId() {
return visitId;
}

@JsonProperty("visitId")
public void setVisitId(String visitId) {
this.visitId = visitId;
}

@JsonProperty("visitNotes")
public String getVisitNotes() {
return visitNotes;
}

@JsonProperty("visitNotes")
public void setVisitNotes(String visitNotes) {
this.visitNotes = visitNotes;
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
