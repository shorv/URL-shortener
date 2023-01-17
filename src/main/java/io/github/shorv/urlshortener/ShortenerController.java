package io.github.shorv.urlshortener;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/shortener")
public class ShortenerController {

    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping()
    ShortenerResponse shortenUrl(@RequestBody ShortenerRequest request) {
        String hash = shortenerService.shortenUrl(request.url());
        return new ShortenerResponse(hash);
    }

    @GetMapping("{hash}")
    ResponseEntity<HttpStatus> resolveUrl(@PathVariable String hash) throws HashUnknownException {
        String url = shortenerService.resolveUrl(hash);

        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(url))
                .header(HttpHeaders.CONNECTION, "close")
                .build();
    }
}
