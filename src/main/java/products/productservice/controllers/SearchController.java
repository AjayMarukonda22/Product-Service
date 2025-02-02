package products.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import products.productservice.dtos.GenericProductDto;
import products.productservice.dtos.SearchRequestDto;
import products.productservice.services.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping
    public Page<GenericProductDto> search(@RequestBody SearchRequestDto searchRequestDto) {
          return searchService.search(searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getPageSize());
    }
}
