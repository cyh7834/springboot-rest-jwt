package yh.simplejwt.restjwt.network;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends Header {
    private List<T> list;
}
