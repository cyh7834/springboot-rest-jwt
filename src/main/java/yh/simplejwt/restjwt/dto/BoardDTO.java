package yh.simplejwt.restjwt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {
    @NotEmpty
    @Size(min=2, max=40)
    @ApiModelProperty(value = "제목", required = true)
    private String title;

    @NotEmpty
    @Size(min=2, max=1500)
    @ApiModelProperty(value = "내용", required = true)
    private String content;
}
