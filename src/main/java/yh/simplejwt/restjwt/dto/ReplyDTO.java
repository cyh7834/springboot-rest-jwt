package yh.simplejwt.restjwt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDTO {
    @NotNull
    @ApiModelProperty(value = "게시글 번호", required = true)
    private long boardNum;

    @NotEmpty
    @Size(min=2, max=500)
    @ApiModelProperty(value = "내용", required = true)
    private String content;
}
