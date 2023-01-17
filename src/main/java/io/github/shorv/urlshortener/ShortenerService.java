package io.github.shorv.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ShortenerService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    private final MessageDigest digest;

    public ShortenerService() throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance("SHA-256");
    }

    public String shortenUrl(String url) {
        String hash = hash(url, 6);
        redisTemplate.opsForValue().set(hash, url);
        return hash;
    }

    private String hash(String url, int length) {
        byte[] bytes = this.digest.digest(url.getBytes());
        String hash = String.format("%32x", new BigInteger(1, bytes));
        return hash.substring(0, length - 1);
    }

    public String resolveUrl(String hash) throws HashUnknownException {
        String url = redisTemplate.opsForValue().get(hash);

        if (url == null) {
            throw new HashUnknownException("Providen hash is unknown. The hash: " + hash);
        }

        return redisTemplate.opsForValue().get(hash);
    }
}
