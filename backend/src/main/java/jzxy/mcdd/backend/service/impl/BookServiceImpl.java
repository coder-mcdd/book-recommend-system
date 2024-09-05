package jzxy.mcdd.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jzxy.mcdd.backend.entity.Book;
import jzxy.mcdd.backend.mapper.BookMapper;
import jzxy.mcdd.backend.service.BookService;
import org.springframework.stereotype.Service;

/**
 * BookServiceImpl
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/29 18:24
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
