package MVC;

/**
 * Created by nsohoni on 24/10/17.
 */

public interface DataFetchListener<T> {
    public  void    success(T data);
    public  void    error();
}
