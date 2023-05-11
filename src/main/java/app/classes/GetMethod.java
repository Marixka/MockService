package app.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMethod {

    private String name;
    private Integer optionId;


    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("optionId")
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @JsonProperty("optionId")
    public Integer getOptionId() {
        return optionId;
    }

}
