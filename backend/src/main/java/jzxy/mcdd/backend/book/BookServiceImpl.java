package jzxy.mcdd.backend.book;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jzxy.mcdd.backend.entity.Book;
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
