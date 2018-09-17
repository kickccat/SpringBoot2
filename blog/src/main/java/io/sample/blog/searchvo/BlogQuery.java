package io.sample.blog.searchvo;

import lombok.Data;

@Data
public class BlogQuery {
    
    private String title;
    private Long typeId;
    private boolean recommendable;
}
