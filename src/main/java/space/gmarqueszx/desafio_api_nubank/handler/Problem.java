package space.gmarqueszx.desafio_api_nubank.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private Map<String, String> errors;
}
