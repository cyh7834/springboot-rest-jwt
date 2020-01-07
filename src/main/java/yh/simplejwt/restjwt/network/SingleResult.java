package yh.simplejwt.restjwt.network;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends Header{
    private T data;
}
