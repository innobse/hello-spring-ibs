package ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "unknown")
public class CarDto extends RepresentationModel<CarDto> {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("fabric")
    private String fabric;

    @JsonProperty("model")
    private String model;

    @JsonProperty("createYear")
    private int createYear;

    @JsonProperty("vin")
    private String vin;

}
