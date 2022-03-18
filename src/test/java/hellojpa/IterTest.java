package hellojpa;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterTest {

    @Test
    public void iterTest() {
        List<String> list = Arrays.asList("a","b","c","d","e","f");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("it = " + it.next());
        }
    }

    @Test
    public void emailRegexTest() {
        final String pattern = "^(([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})([\\,]?[\\s]?))*$";

        List<String> lists = Arrays.asList(
            "ddukda@gmail.com",
            "@ddukda@gmail.com",
            "ddukda@gmail.com,a",
            "ddukda@gmail.com,ddukda@gmail.com",
            "ddukda@gmail.com, ddukda@gmail.com",
            "ddukda@gmail.com , ddukda@gmail.com",
            "ddukda@gmail,com",
            "ddukda@gmail.com ",
            "ddukda@gmail.com,",
            "ddukda@gmail.com ,",
            "ddukda@gmail.com , ",
            "ddukda@gmail.com,, ddukda@gmail.com",
            "ddukda@gmail.com ddukda@gmail.com"

        );

        lists.stream().forEach(s -> System.out.println(s + " : " + Pattern.matches(pattern, s)));
    }


    @Test
    void emailCheckerTest() {
        EmailRegex emailRegex = Mockito.mock(EmailRegex.class);
        BDDMockito.given(emailRegex.match()).willReturn(false);

        String pattern = "^(([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})([\\,]?[\\s]?))*$";
        Assertions.assertEquals(Pattern.matches(pattern, "ddukda@gmail.com"), true);
        Assertions.assertEquals(emailRegex.match(), false);
        
    }

    private class EmailRegex {

        public boolean match() {
            return true;
        }
    }
}
