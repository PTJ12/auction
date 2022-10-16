package xpit.top.auction.utils;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CopySourceName {
    
    String value();

}
