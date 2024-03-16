package com.essentials.controller;

import com.essentials.data.VOv1.BookVOV1;
import com.essentials.data.VOv1.PersonVOV1;
import com.essentials.services.BookService;
import com.essentials.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/book/v1")
@Tag(name = "Book",description = "Endpoints for Managing Books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping (produces = {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML
            , com.essentials.util.MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all Books", description = "Finds all Books", tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = BookVOV1.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            })
    public List<BookVOV1> findAllBooks(){
        List<BookVOV1> booksV1=bookService.findAll();
        // HATEOAS
        booksV1.stream().forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BookController.class)
                                .findByIdBook(p.getKey())).withSelfRel()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        return booksV1;
    }

    @GetMapping(value = "/{id}",produces = {com.essentials.util.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
            ,"application/x-yaml"})
    @Operation(summary = "Finds a Book", description = "Finds a Book", tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content
                                    (schema = @Schema(implementation = BookVOV1.class))

                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            })
    public BookVOV1 findByIdBook(@PathVariable(value = "id") Long id) throws Exception{

        BookVOV1 vov1= bookService.findById(id);

        // HATEOAS
        vov1.add(linkTo(methodOn(BookController.class).findByIdBook(id)).withSelfRel());
        return vov1;
    }

    @PostMapping(consumes =  {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"},
            produces ={ com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"})
    @Operation(summary = "Adds a new Book", description = "Adds a new Book by passing in a JSON,XML or YML", tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content
                                    (schema = @Schema(implementation = BookVOV1.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            })
    public BookVOV1 createBook(@RequestBody BookVOV1 book) throws Exception {
        BookVOV1 bookVO = bookService.createBook(book);
        bookVO.add(linkTo(methodOn(BookController.class).findByIdBook(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @PutMapping(consumes =  {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"},
            produces = {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"})
    @Operation(summary = "Updates a Book", description = "Updates a Book by passing in a JSON,XML or YML", tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content
                                    (schema = @Schema(implementation = BookVOV1.class))

                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            })
    public BookVOV1 updateBook(@RequestBody BookVOV1 book) throws Exception {
        BookVOV1 bookVO = bookService.updatedBook(book);
        bookVO.add(linkTo(methodOn(BookController.class).findByIdBook(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete one Book", description = "Deleted Book", tags = {"Book"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            })
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) throws Exception{

        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
