package twitterapi;

import com.atilla.twitterapi.dto.TweetRequest;
import com.atilla.twitterapi.dto.UpdateTweetRequest;
import com.atilla.twitterapi.entity.Tweet;
import com.atilla.twitterapi.entity.User;
import com.atilla.twitterapi.exception.ForbiddenException;
import com.atilla.twitterapi.exception.TweetNotFoundException;
import com.atilla.twitterapi.repository.TweetRepository;
import com.atilla.twitterapi.repository.UserRepository;
import com.atilla.twitterapi.service.TweetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetServiceTest {
    @Mock
    private TweetRepository tweetRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TweetService tweetService;

    @Test
    void createTweet_shouldCreateTweet_whenUserExists() {
        User user = new User();
        user.setId(1L);
        user.setUsername("atilla");

        TweetRequest request = new TweetRequest();
        request.setContent("Test tweet");
        request.setUserId(1L);

        Tweet savedTweet = new Tweet();
        savedTweet.setId(1L);
        savedTweet.setContent("Test tweet");
        savedTweet.setUser(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(tweetRepository.save(any(Tweet.class))).thenReturn(savedTweet);

        Tweet result = tweetService.createTweet(request);

        assertEquals("Test tweet", result.getContent());
        assertEquals(1L, result.getUser().getId());

        verify(userRepository).findById(1L);
        verify(tweetRepository).save(any(Tweet.class));
    }

    @Test
    void findById_shouldThrowException_whenTweetDoesNotExist() {
        when(tweetRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TweetNotFoundException.class, () -> {
            tweetService.findById(999L);
        });
    }

    @Test
    void updateTweet_shouldUpdateContent_whenTweetExists() {
        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setContent("Old tweet");

        UpdateTweetRequest request = new UpdateTweetRequest();
        request.setContent("Updated tweet");

        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        when(tweetRepository.save(tweet)).thenReturn(tweet);

        Tweet result = tweetService.updateTweet(1L, request);

        assertEquals("Updated tweet", result.getContent());

        verify(tweetRepository).findById(1L);
        verify(tweetRepository).save(tweet);
    }

    @Test
    void deleteTweet_shouldThrowForbiddenException_whenUserIsNotOwner() {
        User owner = new User();
        owner.setId(1L);

        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setUser(owner);

        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));

        assertThrows(ForbiddenException.class, () -> {
            tweetService.deleteTweet(1L, 2L);
        });

        verify(tweetRepository, never()).delete(any(Tweet.class));
    }
}