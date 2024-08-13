package hello.springs.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    //private final MyLogger myLogger;
    private final ObjectProvider<MyLogger> myLoggerProvider;
    public void logic(String id){
        //Provider(DL)로 request scope bean 생성 지연
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
