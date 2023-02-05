package notice.board.response;


import lombok.Getter;
import notice.board.exception.ExceptionCode;

@Getter
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(ExceptionCode e) {
        return new ErrorResponse(e.getStatus(), e.getMessage());
    }
}
