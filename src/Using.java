import java.io.Closeable;
import java.io.IOException;

public class Using {
    static class AutoClose<T extends IOException> implements Closeable {
        public void close() throws T {
        }
    }

    static interface Prod<T> {
        T prod();
    }

    static interface Block<U, T extends IOException> {
        void bar(U u) throws T;
    }

    static <T extends AutoClose<R>, R extends IOException> void using(Prod<T> prod, Block<T, R> exp) throws R {
        final T t = prod.prod();
        try {
            exp.bar(t);
        } finally {
            t.close();
        }
    }

    static class SpecificException extends IOException {
    }

    static class Foo extends AutoClose<SpecificException> {
    }

    public static void main(String... s) throws SpecificException {
        using(
                new Prod<Foo>() {
                    public Foo prod() {
                        return new Foo();
                    }
                },
                new Block<Foo, SpecificException>() {
                    @Override
                    public void bar(Foo foo) throws SpecificException {
                        System.out.println(foo);
                    }
                });
    }
}
