package jzxy.mcdd.backend.exception;

/**
 * PythonServerException
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/9/6 09:47
 */
public class PythonServerException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -2119302295305964305L;

    public PythonServerException() {
    }

    public PythonServerException(String message) {
        super(message);
    }

    public PythonServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PythonServerException(Throwable cause) {
        super(cause);
    }

    public PythonServerException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
