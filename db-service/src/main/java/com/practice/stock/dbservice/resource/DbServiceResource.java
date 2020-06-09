package com.practice.stock.dbservice.resource;

import com.practice.stock.dbservice.model.Quote;
import com.practice.stock.dbservice.model.Quotes;
import com.practice.stock.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private QuotesRepository quotesRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username){

        return quotesRepository.findByUsername(username)
                .stream().map(Quote::getQuote).collect(Collectors.toList());

    }

    @PostMapping("/add")
    public List<String> addQuotes(@RequestBody final Quotes quotes){

        quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUsername(),quote))
                .forEach(quote->quotesRepository.save(quote));
        return quotesRepository.findAll()
                .stream().map(Quote::getQuote).collect(Collectors.toList());


    }
}
