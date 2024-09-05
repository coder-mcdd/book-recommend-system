package jzxy.mcdd.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

@Data
public class Book {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Date publicationDate;
    private String genre;
    private String isbn;
    private String coverImage;
    private String summary;
    private Long rating;
}