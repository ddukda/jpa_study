package hellojpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;

public class BuilderTest {

    @Test
    void builderTest() {
        TestBuilder testBuilder = new TestBuilder();

        TestBuilderResponse testBuilderResponse = testBuilder
                .getProduct()
                .param()
                .productId()
                .execute();

        Assertions.assertNotNull(testBuilderResponse);

    }

    private class TestBuilder {

        public TestBuilder getProduct() {
            return this;
        }

        public TestBuilder param() {
            return this;
        }

        public TestBuilder productId() {
            return this;
        }

        public TestBuilderResponse execute() {
            return new TestBuilderResponse();
        }
    }

    private class TestBuilderResponse {
    }
}
