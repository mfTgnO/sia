package com.example.demo.mapper;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package: com.example.demo.mapper
 * @author:
 * @email:
 * @createDate: 2019-05-23 17:52
 * @description:
 */
@Repository
public interface BookMapper {
    List<Book> selectAllBook();
}
