package testObject;

/**
 * Created by Doohyun on 2017. 5. 10..
 */
public class MessageException extends Exception{
    private String message = "";

    /**
     * @param message
     * 사용자에게 알려줄 메시지를 파라미터로 입력받는다.
     */
    public MessageException(String message){
        super(message);
        this.message = message;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     * 메시지 처리
     */
    @Override
    public String getMessage(){
        return this.message;
    }
}

