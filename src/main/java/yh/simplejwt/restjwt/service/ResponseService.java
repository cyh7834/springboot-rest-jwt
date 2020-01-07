package yh.simplejwt.restjwt.service;

import org.springframework.stereotype.Service;
import yh.simplejwt.restjwt.network.Header;
import yh.simplejwt.restjwt.network.ListResult;
import yh.simplejwt.restjwt.network.SingleResult;

import java.util.List;

@Service
public class ResponseService {

    // api 요청 결과에 대한 코드, 메시지를 정의.
    public enum CommonResponse {
        SUCCESS(0, "성공"),
        FAIL(-1, "실패");

        int code;
        String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    // 단일 결과를 처리.
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 다중 결과를 처리.
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리.
    public Header getSuccessResult() {
        Header result = new Header();
        setSuccessResult(result);
        return result;
    }

    // 실패 결과만 처리.
    public Header getFailResult() {
        Header result = new Header();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    // 코드와 메시지를 추가하여 처리.
    public Header getFailResult(int code, String msg) {
        Header result = new Header();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅.
    private void setSuccessResult(Header result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}