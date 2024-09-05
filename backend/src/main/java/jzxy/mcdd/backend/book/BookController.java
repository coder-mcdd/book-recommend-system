package jzxy.mcdd.backend.book;

import lombok.RequiredArgsConstructor;
import jzxy.mcdd.backend.entity.Book;
import jzxy.mcdd.backend.entity.RestBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/29 18:25
 */
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/list")
    public RestBean<List<Book>> list() {
        return RestBean.success(bookService.list());
    }

    @GetMapping("/details")
    public RestBean<Book> getById(@RequestParam Integer id) {
        return RestBean.success(bookService.getById(id));
    }
}
