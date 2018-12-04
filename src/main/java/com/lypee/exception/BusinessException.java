package com.lypee.exception;

public class BusinessException extends Exception {
    private static final long serialVersionUID = -5587054685087764286L;
    public BusinessException(String message ,Throwable e)
    {
        super(message ,e) ;
    }
    public BusinessException(String message)
    {
        super(message) ;
    }
    public BusinessException(Throwable e)
    {
        super(e);
    }
    @Override
    public Throwable fillInStackTrace()
    {
        return this ;
    }
}
