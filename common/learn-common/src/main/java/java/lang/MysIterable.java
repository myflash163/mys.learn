package java.lang;


import java.util.MysIterator;

public interface MysIterable<T> {

    MysIterator<T> iterator();
}
