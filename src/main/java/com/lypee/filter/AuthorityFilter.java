package com.lypee.filter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class AuthorityFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
