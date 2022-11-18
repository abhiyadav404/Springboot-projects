package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@ApiModel(description = "Post model information")
public class PostDto {
    @ApiModelProperty(value = "Blog post id")
    private long id;

    @ApiModelProperty(value = "Blog post title")
    @NotEmpty
    @Size(min = 2, message = "Post title should have atleast 2 characters")
    private String title;

    @NotEmpty
    @ApiModelProperty(value = "Blog post conent")
    @Size(min = 10, message = "Post description should have atleast 10 characters")
    private String content;

    @NotEmpty
    @ApiModelProperty(value = "Blog post description")
    private String description;

    @ApiModelProperty(value = "Blog post comments")
    private Set<CommentDto> comments;
}
