package runTest;


import Njava.util.business.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by Doohyun on 2017. 3. 20..
 */
public class Test {

    @org.junit.Test
    public void rxSimple() {
        File file = FileUtil.CreateFileObject("/Users/namduhyeon/git_repository/Test/TestPath/ok.jsp");

        try {
            FileUtil.CreateFile(file);
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(FileUtil.GetDirectoryPath(file.getPath()));
    }
}
