package MVC;

/**
 * Created by nsohoni on 24/10/17.
 */

public interface DataFetchListener<T> {
    void    success(T data);
    void    error();
}
