package com.hong.spendo.common;





import com.hong.spendo.enums.ResponseStatus;

import lombok.Data;

@Data
public class ResponseEntity<T> {

	private ResponseStatus status;
    private String msg;
    private T data;

    /** 등록,수정,삭제 응답값 */
    public ResponseEntity (ResponseStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    /** 목록,상세정보 응답값 */
    public ResponseEntity (ResponseStatus status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseEntity<T> of(ResponseStatus status, String msg) {
        return new ResponseEntity<>(status,msg);
    }
    
    public static <T> ResponseEntity<T> of(ResponseStatus status, String msg, T data) {
        return new ResponseEntity<>(status,msg, data);
    }
    
}
